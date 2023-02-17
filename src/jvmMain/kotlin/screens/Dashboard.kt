package tb.sampleapps.pistolwallet.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tb.sampleapps.pistolwallet.LdkNode

@Composable
fun Dashboard() {
    var nodeId by remember { mutableStateOf(" ") }
    var balance by remember { mutableStateOf(" ") }
    var fundingAddress by remember { mutableStateOf("") }
    var errorMessages by remember { mutableStateOf("") }
    var nodeIsLive by remember { mutableStateOf(false) }
    // nodeIsLive = LdkNode.node != null

    Column {
        Title("Node Dashboard")
        Column(
            Modifier
                .padding(start = 140.dp)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(start = 24.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                if (nodeIsLive) {
                    Text("Node status: \uD83D\uDFE2 Online")
                } else {
                    Text("Node status: \uD83D\uDD34 Offline")
                }
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = balance,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Monospace
                )
                Divider(
                    modifier = Modifier.width(500.dp),
                    color = Color(0xff4f4f4f),
                    thickness = 1.dp
                )
                Text("  Onchain balance")
                Spacer(modifier = Modifier.height(32.dp))

                SelectionContainer {
                    Text(
                        text = nodeId,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
                Divider(
                    modifier = Modifier.width(500.dp),
                    color = Color(0xff4f4f4f),
                    thickness = 1.dp
                )
                Text("  Node pubkey")

                Spacer(modifier = Modifier.height(32.dp))
                SelectionContainer {
                    Text(
                        text = fundingAddress,
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
                Divider(
                    modifier = Modifier.width(500.dp),
                    color = Color(0xff4f4f4f),
                    thickness = 1.dp
                )
                Text("  Onchain wallet funding address")

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (nodeIsLive) {
                        Button(
                            enabled = false,
                            onClick = { },
                            modifier = Modifier
                                .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                                .height(170.dp)
                                .width(170.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xff4f4f4f),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Start your node")
                        }
                    } else {
                        Button(
                            onClick = {
                                try {
                                    LdkNode.buildNode()
                                    LdkNode.node?.start()
                                    nodeIsLive = true
                                } catch (e: Exception) {
                                    errorMessages = "Error starting node: ${e.message}"
                                }
                                nodeId = "${LdkNode.node?.nodeId()}"
                            },
                            modifier = Modifier
                                .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                                .height(170.dp)
                                .width(170.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xff4f4f4f),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Start your node")
                        }
                    }
                    Column {
                        Row {
                            Button(
                                onClick = {
                                    LdkNode.node?.syncWallets()
                                    balance = "${LdkNode.node?.totalOnchainBalanceSats()} sat"
                                },
                                enabled = nodeIsLive,
                                modifier = Modifier
                                    .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                                    .height(80.dp)
                                    .width(150.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(0xff4f4f4f),
                                    contentColor = Color.White
                                )
                            ) {
                                Text("Get balance")
                            }
                            Button(
                                onClick = {
                                    fundingAddress = "${LdkNode.node?.newFundingAddress()}"
                                },
                                enabled = nodeIsLive,
                                modifier = Modifier
                                    .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                                    .height(80.dp)
                                    .width(150.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(0xff4f4f4f),
                                    contentColor = Color.White
                                )
                            ) {
                                Text(
                                    text = "Get funding\naddress",
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                        Row {
                            Button(
                                onClick = {
                                    LdkNode.node?.syncWallets()
                                },
                                enabled = nodeIsLive,
                                modifier = Modifier
                                    .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                                    .height(80.dp)
                                    .width(150.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(0xff4f4f4f),
                                    contentColor = Color.White
                                )
                            ) {
                                Text("Sync wallet")
                            }
                            Button(
                                onClick = {  },
                                enabled = false,
                                modifier = Modifier
                                    .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                                    .height(80.dp)
                                    .width(150.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(0xff4f4f4f),
                                    contentColor = Color.White
                                )
                            ) {
                                Text("")
                            }
                        }
                    }
                }
            }
            Text(errorMessages)
        }
    }
}