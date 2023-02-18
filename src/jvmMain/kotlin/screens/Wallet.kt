package tb.sampleapps.pistolwallet.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tb.sampleapps.pistolwallet.LdkNode

@Composable
fun Wallet() {
    var invoice by remember { mutableStateOf("") }

    Column {
        Title("Lightning Wallet")
        Column(
            Modifier.padding(start = 140.dp).fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 48.dp, bottom = 48.dp)
            ) {
                Text(
                    text = "Pay invoice",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xff1f0208),
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp, bottom = 16.dp)
                )
                TextField(
                    value = invoice,
                    onValueChange = { invoice = it },
                    placeholder = {
                        Text("Invoice")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )

                Row {

                    Button(
                        onClick = {
                            LdkNode.payInvoice(invoice)
                        },
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 8.dp, start = 24.dp, end = 8.dp)
                            .height(90.dp)
                            .width(180.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xff4f4f4f),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Pay invoice")
                    }

                    Button(
                        onClick = {
                            LdkNode.receivePayment()
                        },
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 8.dp, start = 24.dp, end = 8.dp)
                            .height(90.dp)
                            .width(180.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xff4f4f4f),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Receive payment",
                            textAlign = TextAlign.Center,
                        )
                    }
                }

                Row {
                    Button(
                        onClick = {
                            LdkNode.nextEvent()
                        },
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 8.dp, start = 24.dp, end = 8.dp)
                            .height(90.dp)
                            .width(180.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xff4f4f4f),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Next event",
                            textAlign = TextAlign.Center,
                        )
                    }

                    Button(
                        onClick = {
                            LdkNode.eventHandled()
                        },
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 8.dp, start = 24.dp, end = 8.dp)
                            .height(90.dp)
                            .width(180.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xff4f4f4f),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Event handled",
                            textAlign = TextAlign.Center,
                        )
                    }
                }


            }
        }
    }
}
