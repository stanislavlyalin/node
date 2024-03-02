package node.comm

import node.comm.Config.PeerCfg
import sdk.reflect.Description

@Description("comm")
final case class Config(
  @Description("Initial list of node peers")
  peers: List[PeerCfg] = List.empty[PeerCfg],
)

object Config {
  final case class PeerCfg(
    host: String,
    port: Int,
    isSelf: Boolean,
    isValidator: Boolean,
  )

  // Default config has an example value as a basis when filling config manually
  val Default: Config = Config(List(PeerCfg("host", port = 1234, isSelf = false, isValidator = true)))
}
