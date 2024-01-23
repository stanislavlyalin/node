package coop.rchain.rholang.normalizer2.syntax

import cats.{Applicative, Apply, Functor}
import cats.effect.Sync
import cats.implicits.{toFlatMapOps, toFunctorOps}
import coop.rchain.rholang.interpreter.compiler.{FreeContext, IdContext}
import coop.rchain.rholang.normalizer2.env.*
import coop.rchain.rholang.syntax.normalizerEffectSyntax

trait EffectSyntax {
  implicit def normalizerEffectSyntax[F[_], A](f: F[A]): NormalizerEffectOps[F, A] = new NormalizerEffectOps[F, A](f)
}

class NormalizerEffectOps[F[_], A](val f: F[A]) extends AnyVal {

  /** Run a function within a new scope, label it as a pattern
   * @param inReceive Flag should be true for pattern in receive (input) or contract. */
  def asPatternWithoutFreeExtracting(
    inReceive: Boolean = false,
  )(implicit bwScope: BoundVarScope[F], fwScope: FreeVarScope[F], rWriter: NestingInfoWriter[F]): F[A] =
    bwScope.withNewBoundVarScope(fwScope.withNewFreeVarScope(rWriter.markAsPattern(inReceive)(f)))

  /** Run a function within a new scope, label it as a pattern,
   * and subsequently extract all free variables from the normalized result of this function.
   * @param inReceive Flag should be true for pattern in receive (input) or contract. */
  def asPattern[T](inReceive: Boolean = false)(implicit
    functor: Functor[F],
    bwScope: BoundVarScope[F],
    fwScope: FreeVarScope[F],
    rWriter: NestingInfoWriter[F],
    fwReader: FreeVarReader[T],
  ): F[(A, Seq[(String, FreeContext[T])])] = f.asPatternWithoutFreeExtracting(inReceive).map((_, fwReader.getFreeVars))

  /** Run function with restricted conditions with restrictions as for the bundle */
  def asBundle()(implicit rWriter: NestingInfoWriter[F]): F[A] = rWriter.markAsBundle(f)

  /** Bound free variables in a copy of the current scope.
   *
   * Free variables are sorted by levels and then added with indexes:
   * {i0, i1, ..., iN} = {fl0 + last + 1, fl1 + last + 1, ..., flN + last + 1}.
   * Here, i0, ..., iN represent the Bruijn indices of the new bound vars,
   * fl0, ..., flN are the Bruijn levels of the inserted free vars,
   * last is the last index among all bound vars at the moment.
   */
  def withAbsorbedFreeVars[T](
    freeVars: Seq[(String, FreeContext[T])],
  )(implicit sync: Sync[F], bwScope: BoundVarScope[F], bwWriter: BoundVarWriter[T]): F[A] = {

    def absorbFree(freeVars: Seq[(String, FreeContext[T])]): Seq[IdContext[T]] = {
      val sortedByLevel  = freeVars.sortBy(_._2.level)
      val (levels, data) = sortedByLevel.unzip(fv => (fv._2.level, (fv._1, fv._2.typ, fv._2.sourcePosition)))
      assert(
        levels == levels.indices,
        "Error when absorbing free variables during normalization: incorrect de Bruijn levels." +
          s"Should be ${levels.indices}, but was $levels.",
      )
      data
    }
    f.withNewBoundVars(absorbFree(freeVars)).map(_._1)
  }

  /** Put new bound variables in a copy of the current scope.
   * @return result of the effect and the number of inserted non-duplicate variables
   */
  def withNewBoundVars[T](
    boundVars: Seq[IdContext[T]],
  )(implicit sync: Sync[F], bwScope: BoundVarScope[F], bwWriter: BoundVarWriter[T]): F[(A, Int)] =
    bwScope.withCopyBoundVarScope(for {
      bindCount <- sync.delay(bwWriter.putBoundVars(boundVars))
      fRes      <- f
    } yield (fRes, bindCount))
}
