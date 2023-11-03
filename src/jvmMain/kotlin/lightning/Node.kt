package tb.sampleapps.pistolwallet.lightning

import org.lightningdevkit.ldknode.*
import org.lightningdevkit.ldknode.LdkNode

object LdkNode {
    val node: LdkNode by lazy {
        val nodeBuilder = Builder.fromConfig(ldkConfig)
        nodeBuilder.build()
    }

    private val ldkConfig = Config(
        storageDirPath = "./pistol-wallet-data",
        network = Network.REGTEST,
        listeningAddress = "127.0.0.1:2323",
        defaultCltvExpiryDelta = 2048u,
        onchainWalletSyncIntervalSecs = 20uL,
        walletSyncIntervalSecs = 20uL,
        feeRateCacheUpdateIntervalSecs = 1000uL,
        logLevel = LogLevel.ERROR,
        trustedPeers0conf = listOf()
    )

    fun openChannel(
        nodeId: String,
        address: String,
        channelAmountSats: ULong,
        pushToCounterpartyMsat: ULong,
        announceChannel: Boolean
    ) {
        try {
            node.connectOpenChannel(
                nodeId = nodeId,
                address = address,
                channelAmountSats = channelAmountSats,
                pushToCounterpartyMsat = pushToCounterpartyMsat,
                channelConfig = null,
                announceChannel = announceChannel,
            )
        } catch (e: Exception) {
            println("Error opening channel: ${e.message}")
        }
    }

    fun payInvoice(invoice: String) {
        val invoice: Invoice = invoice
        try {
            node.sendPayment(invoice)
        } catch (e: Exception) {
            println("Error paying invoice: ${e.message}")
        }
    }

    fun receivePayment() {
        println(node.receivePayment(6024_000uL, "test", 1000u))
    }

    fun nextEvent() {
        val nextEvent: Event? = node.nextEvent()
        println(nextEvent?.toString() ?: "No event for now")
    }

    fun eventHandled() {
        node.eventHandled()
    }
}
