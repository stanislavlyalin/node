package squeryl

import cats.effect.Sync
import cats.syntax.all.*
import sdk.api.BlockJustificationsDbApi
import sdk.api.data.*
import sdk.db.DbSession
import sdk.db.DbSession.withSessionF
import squeryl.RhonixNodeDb.blockJustificationsTable
import squeryl.tables.BlockJustificationsTable
import squeryl.tables.CustomTypeMode.*

class BlockJustificationsDbApiImpl[F[_]: Sync: DbSession] extends BlockJustificationsDbApi[F] {
  override def insert(blockJustifications: BlockJustifications): F[BlockJustifications] =
    withSessionF(blockJustificationsTable.insert(BlockJustificationsTable.toDb(blockJustifications)))
      .map(BlockJustificationsTable.fromDb)

  override def getByBlock(latestBlockId: Long): F[Seq[BlockJustifications]] =
    withSessionF(
      blockJustificationsTable.where(_.latestBlockId === latestBlockId).map(BlockJustificationsTable.fromDb).toSeq,
    )
}

object BlockJustificationsDbApiImpl {
  def apply[F[_]: BlockJustificationsDbApiImpl]: BlockJustificationsDbApiImpl[F] =
    implicitly[BlockJustificationsDbApiImpl[F]]
}
