package tb.sampleapps.pistolwallet

import org.lightningdevkit.ldknode.Builder
import org.lightningdevkit.ldknode.Config
import org.lightningdevkit.ldknode.Node

object LdkNode {
    var node: Node? = null

    fun buildNode() {
        val ldkConfig = Config(
            storageDirPath = "./pistol-wallet-data",
            esploraServerUrl = "http://127.0.0.1:3002",
            network = "regtest",
            listeningAddress = "127.0.0.1:2323",
            defaultCltvExpiryDelta = 2048u,
        )
        val nodeBuilder = Builder.fromConfig(ldkConfig)
        node = nodeBuilder.build()
    }

    fun initialize() {
        println("Node initialized")
    }

    fun openChannel(nodePukeyAndAddress: String, channelAmountSats: ULong) {
        node?.connectOpenChannel(
            nodePubkeyAndAddress = nodePukeyAndAddress,
            channelAmountSats = channelAmountSats,
            announceChannel = true
        )
    }
}
