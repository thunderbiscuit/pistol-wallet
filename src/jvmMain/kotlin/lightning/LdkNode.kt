package tb.sampleapps.pistolwallet.lightning

import org.lightningdevkit.ldknode.Address
import org.lightningdevkit.ldknode.LdkNode
import org.lightningdevkit.ldknode.Config
import org.lightningdevkit.ldknode.Network
import org.lightningdevkit.ldknode.Invoice
import org.lightningdevkit.ldknode.Builder
import org.lightningdevkit.ldknode.Event
import org.lightningdevkit.ldknode.LogLevel

object LdkNode {
    val node: LdkNode by lazy {
        val nodeBuilder = Builder()
        // this doesn't work at the moment
        // val nodeBuilder = Builder.fromConfig(ldkConfig)
        nodeBuilder.setStorageDirPath("./pistol-wallet")
        nodeBuilder.setNetwork(Network.REGTEST)
        nodeBuilder.setListeningAddress("127.0.0.1:2323")
        val node: LdkNode = nodeBuilder.build()

        node
    }

    fun start() {
        node.start()
    }

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

    fun syncWallets() {
        node.syncWallets()
    }

    fun newOnchainAddress(): String {
        val address: Address = node.newOnchainAddress()
        return address
    }
}

// If I try to use this object in the builder, it will return an error
// saying "failed to setup the logger".
private val ldkConfig = Config(
    storageDirPath = "./pistol-wallet",
    network = Network.REGTEST,
    listeningAddress = "127.0.0.1:2323",
    defaultCltvExpiryDelta = 2048u,
    onchainWalletSyncIntervalSecs = 20uL,
    walletSyncIntervalSecs = 20uL,
    feeRateCacheUpdateIntervalSecs = 1000uL,
    logLevel = LogLevel.ERROR,
    trustedPeers0conf = listOf()
)
