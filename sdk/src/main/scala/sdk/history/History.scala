package sdk.history

import cats.Parallel
import cats.effect.{Async, Sync}
import sdk.hashing.Blake2b256Hash
import sdk.history.instances.RadixHistory
import sdk.store.KeyValueStore

/** History definition represents key-value API for RSpace tuple space */
trait History[F[_]] {

  /** Read operation on the Merkle tree */
  def read(key: KeySegment): F[Option[Blake2b256Hash]]

  /** Insert/update/delete operations on the underlying Merkle tree (key-value store) */
  def process(actions: List[HistoryAction]): F[History[F]]

  /** Get the root of the Merkle tree */
  def root: Blake2b256Hash

  /** Returns History with specified root pointer */
  def reset(root: Blake2b256Hash): F[History[F]]
}

object History {
  val EmptyRootHash: Blake2b256Hash = RadixHistory.EmptyRootHash

  def create[F[_]: Async: Sync: Parallel](
    root: Blake2b256Hash,
    store: KeyValueStore[F],
  ): F[RadixHistory[F]] = RadixHistory(root, RadixHistory.createStore(store))
}