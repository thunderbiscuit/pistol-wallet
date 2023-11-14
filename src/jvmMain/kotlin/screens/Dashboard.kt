package tb.sampleapps.pistolwallet.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tb.sampleapps.pistolwallet.lightning.LdkNode

@Composable
fun Dashboard() {
    var nodeId by remember { mutableStateOf("") }
    var balance by remember { mutableStateOf("") }
    var fundingAddress by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var nodeIsLive by remember { mutableStateOf(false) }

    var showCustomSnackbar by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().padding(bottom = 16.dp)) {
        Column {
            Title("Node Dashboard")
            Column {
                Column(
                    modifier = Modifier.fillMaxSize().padding(start = 24.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Spacer(modifier = Modifier.height(24.dp))

                    // Node status
                    if (nodeIsLive) {
                        Text("Node status: \uD83D\uDFE2 Online")
                    } else {
                        Text("Node status: \uD83D\uDD34 Offline")
                    }
                    Spacer(modifier = Modifier.height(24.dp))

                    // Onchain balance
                    Text("Onchain balance:")
                    Text(
                        text = balance,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace
                    )

                    // Node pubkey
                    Text("Node pubkey:")
                    Text(
                        text = nodeId,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace
                    )

                    // Onchain funding address
                    Text("Onchain funding address:")
                    Text(
                        text = fundingAddress,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Row 1: Start node, sync wallet
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            Button(
                                onClick = {
                                    LdkNode.node
                                    LdkNode.start()
                                    nodeIsLive = true
                                },
                                enabled = nodeIsLive.not(),
                                modifier = Modifier
                                    .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                                    .height(80.dp)
                                    .width(190.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White,
                                    backgroundColor = Color(0xff26b8a2),
                                )
                            ) {
                                Text("Start node")
                            }

                            Button(
                                onClick = {
                                    LdkNode.syncWallets()
                                },
                                enabled = nodeIsLive,
                                modifier = Modifier
                                    .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                                    .height(80.dp)
                                    .width(190.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White,
                                    backgroundColor = Color(0xff26b8a2),
                                )
                            ) {
                                Text("Sync wallet")
                            }
                        }

                        // Row 2: Get balance, get funding address
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            Button(
                                onClick = {
                                    LdkNode.node.syncWallets()
                                    balance =
                                        "${LdkNode.node.totalOnchainBalanceSats()}sat and ${LdkNode.node.spendableOnchainBalanceSats()}sat spendable"
                                },
                                enabled = nodeIsLive,
                                modifier = Modifier
                                    .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                                    .height(80.dp)
                                    .width(190.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White,
                                    backgroundColor = Color(0xff26b8a2),
                                )
                            ) {
                                Text("Get balance")
                            }
                            Button(
                                onClick = {
                                    fundingAddress = LdkNode.newOnchainAddress()
                                },
                                enabled = nodeIsLive,
                                modifier = Modifier
                                    .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                                    .height(80.dp)
                                    .width(190.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White,
                                    backgroundColor = Color(0xff26b8a2),
                                )
                            ) {
                                Text(
                                    text = "Get funding\naddress",
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }

                // This spacer takes up all the space in between the core content and the snackbar
                Spacer(Modifier.weight(1f, fill = true))

                Row {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {

                        // Button to trigger snackbar visibility
                        // Button(onClick = { showCustomSnackbar = true }) {
                        //     Text("Show Snackbar")
                        // }

                        if (showCustomSnackbar) {
                            CustomSnackbar(
                                message = "This is a snackbar message!",
                                onDismiss = { showCustomSnackbar = false }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomSnackbar(message: String, onDismiss: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFF323232),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.DarkGray)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = message,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = { onDismiss() },
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xff4f4f4f),
                        contentColor = Color.White
                    )
                ) {
                    Text("Dismiss")
                }
            }
        }
    }
}
