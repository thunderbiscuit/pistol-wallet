package tb.sampleapps.pistolwallet

import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.Modifier
import org.lightningdevkit.ldknode.*

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, Lightning!") }
    var balance by remember { mutableStateOf("Your onchain balance is :") }
    val node: Node by remember { mutableStateOf(createNode()) }
    var fundingAddress by remember { mutableStateOf("Create a new funding address") }
    var errorMessages by remember { mutableStateOf("Error Messages:") }

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text)
            Text(balance)
            SelectionContainer {
                Text(fundingAddress)
            }
            Text(errorMessages)

            Button(
                onClick = {
                    try {
                        node.start()
                    } catch (e: Exception) {
                        errorMessages = "Error starting node: ${e.message}"
                    }
                    text = "Your node ID is\n${node.nodeId()}"
                }
            ) {
                Text("Create a node")
            }
            Button(
                onClick = {
                    node.syncWallets()
                    balance = "Your total onchain balance is : ${node.totalOnchainBalanceSats()} sat"
                }
            ) {
                Text("Get balance")
            }

            Button(
                onClick = {
                    fundingAddress = "Fund your wallet by sending sats to: ${node.newFundingAddress()}"
                }
            ) {
                Text("Get funding address")
            }
        }
    }
}

fun createNode(): Node {
    val ldkConfig = Config(
        storageDirPath = "./pistol-wallet-data",
        esploraServerUrl = "http://127.0.0.1:3002",
        network = "regtest",
        listeningAddress = "127.0.0.1:2323",
        defaultCltvExpiryDelta = 2048u,
    )
    val nodeBuilder = Builder.fromConfig(ldkConfig)
    return nodeBuilder.build()
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
