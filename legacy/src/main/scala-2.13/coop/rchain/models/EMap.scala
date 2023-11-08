// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package coop.rchain.models
import coop.rchain.models.BitSetBytesMapper.bitSetBytesMapper

@SerialVersionUID(0L)
final case class EMap(
    kvs: _root_.scala.Seq[coop.rchain.models.KeyValuePair] = _root_.scala.Seq.empty,
    locallyFree: coop.rchain.models.AlwaysEqual[scala.collection.immutable.BitSet] = coop.rchain.models.EMap._typemapper_locallyFree.toCustom(_root_.com.google.protobuf.ByteString.EMPTY),
    connectiveUsed: _root_.scala.Boolean = false,
    remainder: _root_.scala.Option[coop.rchain.models.Var] = _root_.scala.None
    ) extends coop.rchain.models.StacksafeMessage[EMap] with scalapb.lenses.Updatable[EMap] {

    def mergeFromM[F[_]: cats.effect.Sync](`_input__`: _root_.com.google.protobuf.CodedInputStream): F[coop.rchain.models.EMap] = {
      
      import cats.effect.Sync
      import cats.syntax.all.*
      
      Sync[F].defer {
        val __kvs = (new _root_.scala.collection.immutable.VectorBuilder[coop.rchain.models.KeyValuePair] ++= this.kvs)
        var __locallyFree = this.locallyFree
        var __connectiveUsed = this.connectiveUsed
        var __remainder = this.remainder
        var _done__ = false
        
        Sync[F].whileM_ (Sync[F].delay { !_done__ }) {
          for {
            _tag__ <- Sync[F].delay { _input__.readTag() }
            _ <- _tag__ match {
              case 0 => Sync[F].delay { _done__ = true }
              case 10 =>
                for {
                  readValue       <- coop.rchain.models.SafeParser.readMessage(_input__, coop.rchain.models.KeyValuePair.defaultInstance)
                  customTypeValue =  readValue
                  _               <- Sync[F].delay { __kvs += customTypeValue }
                } yield ()
              case 26 =>
                for {
                  readValue       <- Sync[F].delay { _input__.readBytes() }
                  customTypeValue =  coop.rchain.models.EMap._typemapper_locallyFree.toCustom(readValue)
                  _               <- Sync[F].delay { __locallyFree = customTypeValue }
                } yield ()
              case 32 =>
                for {
                  readValue       <- Sync[F].delay { _input__.readBool() }
                  customTypeValue =  readValue
                  _               <- Sync[F].delay { __connectiveUsed = customTypeValue }
                } yield ()
              case 42 =>
                for {
                  readValue       <- coop.rchain.models.SafeParser.readMessage(_input__, __remainder.getOrElse(coop.rchain.models.Var.defaultInstance))
                  customTypeValue =  readValue
                  _               <- Sync[F].delay { __remainder = Option(customTypeValue) }
                } yield ()
            case tag => Sync[F].delay { _input__.skipField(tag) }
            }
          } yield ()
        }
        .map { _ => coop.rchain.models.EMap(
          kvs = __kvs.result(),
          locallyFree = __locallyFree,
          connectiveUsed = __connectiveUsed,
          remainder = __remainder
        )}
      }
    }
    
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      kvs.foreach { __item =>
        val __value = __item
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      }
      
      {
        val __value = coop.rchain.models.EMap._typemapper_locallyFree.toBase(locallyFree)
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeBytesSize(3, __value)
        }
      };
      
      {
        val __value = connectiveUsed
        if (__value != false) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeBoolSize(4, __value)
        }
      };
      if (remainder.isDefined) {
        val __value = remainder.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      __size
    }
    override def serializedSize: _root_.scala.Int = {
      var __size = __serializedSizeMemoized
      if (__size == 0) {
        __size = __computeSerializedSize() + 1
        __serializedSizeMemoized = __size
      }
      __size - 1
      
    }
    
    @transient var _serializedSizeM: coop.rchain.models.Memo[Int] = null
    
    def serializedSizeM: coop.rchain.models.Memo[Int] = synchronized {
      if(_serializedSizeM == null) {
        _serializedSizeM = new coop.rchain.models.Memo(coop.rchain.models.ProtoM.serializedSize(this))
        _serializedSizeM
      } else _serializedSizeM
    }
    def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): _root_.scala.Unit = {
      kvs.foreach { __v =>
        val __m = __v
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      {
        val __v = coop.rchain.models.EMap._typemapper_locallyFree.toBase(locallyFree)
        if (!__v.isEmpty) {
          _output__.writeBytes(3, __v)
        }
      };
      {
        val __v = connectiveUsed
        if (__v != false) {
          _output__.writeBool(4, __v)
        }
      };
      remainder.foreach { __v =>
        val __m = __v
        _output__.writeTag(5, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
    }
    def clearKvs = copy(kvs = _root_.scala.Seq.empty)
    def addKvs(__vs: coop.rchain.models.KeyValuePair *): EMap = addAllKvs(__vs)
    def addAllKvs(__vs: Iterable[coop.rchain.models.KeyValuePair]): EMap = copy(kvs = kvs ++ __vs)
    def withKvs(__v: _root_.scala.Seq[coop.rchain.models.KeyValuePair]): EMap = copy(kvs = __v)
    def withLocallyFree(__v: coop.rchain.models.AlwaysEqual[scala.collection.immutable.BitSet]): EMap = copy(locallyFree = __v)
    def withConnectiveUsed(__v: _root_.scala.Boolean): EMap = copy(connectiveUsed = __v)
    def getRemainder: coop.rchain.models.Var = remainder.getOrElse(coop.rchain.models.Var.defaultInstance)
    def clearRemainder: EMap = copy(remainder = _root_.scala.None)
    def withRemainder(__v: coop.rchain.models.Var): EMap = copy(remainder = Option(__v))
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => kvs
        case 3 => {
          val __t = coop.rchain.models.EMap._typemapper_locallyFree.toBase(locallyFree)
          if (__t != _root_.com.google.protobuf.ByteString.EMPTY) __t else null
        }
        case 4 => {
          val __t = connectiveUsed
          if (__t != false) __t else null
        }
        case 5 => remainder.orNull
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PRepeated(kvs.iterator.map(_.toPMessage).toVector)
        case 3 => _root_.scalapb.descriptors.PByteString(coop.rchain.models.EMap._typemapper_locallyFree.toBase(locallyFree))
        case 4 => _root_.scalapb.descriptors.PBoolean(connectiveUsed)
        case 5 => remainder.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: coop.rchain.models.EMap.type = coop.rchain.models.EMap
    // @@protoc_insertion_point(GeneratedMessage[EMap])
}

object EMap extends scalapb.GeneratedMessageCompanion[coop.rchain.models.EMap] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[coop.rchain.models.EMap] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): coop.rchain.models.EMap = {
    val __kvs: _root_.scala.collection.immutable.VectorBuilder[coop.rchain.models.KeyValuePair] = new _root_.scala.collection.immutable.VectorBuilder[coop.rchain.models.KeyValuePair]
    var __locallyFree: _root_.com.google.protobuf.ByteString = _root_.com.google.protobuf.ByteString.EMPTY
    var __connectiveUsed: _root_.scala.Boolean = false
    var __remainder: _root_.scala.Option[coop.rchain.models.Var] = _root_.scala.None
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __kvs += _root_.scalapb.LiteParser.readMessage[coop.rchain.models.KeyValuePair](_input__)
        case 26 =>
          __locallyFree = _input__.readBytes()
        case 32 =>
          __connectiveUsed = _input__.readBool()
        case 42 =>
          __remainder = Option(__remainder.fold(_root_.scalapb.LiteParser.readMessage[coop.rchain.models.Var](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case tag => _input__.skipField(tag)
      }
    }
    coop.rchain.models.EMap(
        kvs = __kvs.result(),
        locallyFree = coop.rchain.models.EMap._typemapper_locallyFree.toCustom(__locallyFree),
        connectiveUsed = __connectiveUsed,
        remainder = __remainder
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[coop.rchain.models.EMap] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      coop.rchain.models.EMap(
        kvs = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Seq[coop.rchain.models.KeyValuePair]]).getOrElse(_root_.scala.Seq.empty),
        locallyFree = coop.rchain.models.EMap._typemapper_locallyFree.toCustom(__fieldsMap.get(scalaDescriptor.findFieldByNumber(3).get).map(_.as[_root_.com.google.protobuf.ByteString]).getOrElse(_root_.com.google.protobuf.ByteString.EMPTY)),
        connectiveUsed = __fieldsMap.get(scalaDescriptor.findFieldByNumber(4).get).map(_.as[_root_.scala.Boolean]).getOrElse(false),
        remainder = __fieldsMap.get(scalaDescriptor.findFieldByNumber(5).get).flatMap(_.as[_root_.scala.Option[coop.rchain.models.Var]])
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = RhoTypesProto.javaDescriptor.getMessageTypes().get(19)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = RhoTypesProto.scalaDescriptor.messages(19)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = coop.rchain.models.KeyValuePair
      case 5 => __out = coop.rchain.models.Var
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = coop.rchain.models.EMap(
    kvs = _root_.scala.Seq.empty,
    locallyFree = coop.rchain.models.EMap._typemapper_locallyFree.toCustom(_root_.com.google.protobuf.ByteString.EMPTY),
    connectiveUsed = false,
    remainder = _root_.scala.None
  )
  implicit class EMapLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, coop.rchain.models.EMap]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, coop.rchain.models.EMap](_l) {
    def kvs: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Seq[coop.rchain.models.KeyValuePair]] = field(_.kvs)((c_, f_) => c_.copy(kvs = f_))
    def locallyFree: _root_.scalapb.lenses.Lens[UpperPB, coop.rchain.models.AlwaysEqual[scala.collection.immutable.BitSet]] = field(_.locallyFree)((c_, f_) => c_.copy(locallyFree = f_))
    def connectiveUsed: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Boolean] = field(_.connectiveUsed)((c_, f_) => c_.copy(connectiveUsed = f_))
    def remainder: _root_.scalapb.lenses.Lens[UpperPB, coop.rchain.models.Var] = field(_.getRemainder)((c_, f_) => c_.copy(remainder = Option(f_)))
    def optionalRemainder: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Option[coop.rchain.models.Var]] = field(_.remainder)((c_, f_) => c_.copy(remainder = f_))
  }
  final val KVS_FIELD_NUMBER = 1
  final val LOCALLYFREE_FIELD_NUMBER = 3
  final val CONNECTIVE_USED_FIELD_NUMBER = 4
  final val REMAINDER_FIELD_NUMBER = 5
  @transient
  private[models] val _typemapper_locallyFree: _root_.scalapb.TypeMapper[_root_.com.google.protobuf.ByteString, coop.rchain.models.AlwaysEqual[scala.collection.immutable.BitSet]] = implicitly[_root_.scalapb.TypeMapper[_root_.com.google.protobuf.ByteString, coop.rchain.models.AlwaysEqual[scala.collection.immutable.BitSet]]]
  def of(
    kvs: _root_.scala.Seq[coop.rchain.models.KeyValuePair],
    locallyFree: coop.rchain.models.AlwaysEqual[scala.collection.immutable.BitSet],
    connectiveUsed: _root_.scala.Boolean,
    remainder: _root_.scala.Option[coop.rchain.models.Var]
  ): _root_.coop.rchain.models.EMap = _root_.coop.rchain.models.EMap(
    kvs,
    locallyFree,
    connectiveUsed,
    remainder
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[EMap])
}