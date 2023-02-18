package tb.sampleapps.pistolwallet

import org.lightningdevkit.ldknode.*

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
        try {
            node?.connectOpenChannel(
                nodePubkeyAndAddress = nodePukeyAndAddress,
                channelAmountSats = channelAmountSats,
                announceChannel = true
            )
        } catch (e: Exception) {
            println("Error opening channel: ${e.message}")
        }
    }

    fun payInvoice(invoice: String) {
        val invoice: Invoice = invoice
        try {
            node?.sendPayment(invoice)
        } catch (e: Exception) {
            println("Error paying invoice: ${e.message}")
        }
    }

    fun receivePayment() {
        println(node?.receivePayment(6024_000uL, "test", 1000u).toString())
    }

    fun nextEvent() {
        val nextEvent: Event? = node?.nextEvent()
        println(nextEvent?.toString() ?: "No event for now")
    }

    fun eventHandled() {
        node?.eventHandled()
    }
}
