package tb.sampleapps.pistolwallet.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tb.sampleapps.pistolwallet.lightning.LdkNode

@Composable
fun Peers() {
    var pubkey by remember { mutableStateOf("") }
    var ip by remember { mutableStateOf("") }
    var port by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column {
        Title("Channels")
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Your current channels",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xff1f0208),
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp, bottom = 16.dp)
                )
            }
            Column(
                modifier = Modifier.padding(top = 48.dp, bottom = 48.dp)
            ) {
                Text(
                    text = "Add a channel",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xff1f0208),
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp, bottom = 16.dp)
                )
                TextField(
                    value = pubkey,
                    onValueChange = { pubkey = it },
                    placeholder = {
                        Text("Peer pubkey")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )
                TextField(
                    value = ip,
                    onValueChange = { ip = it },
                    placeholder = {
                        Text("IP address")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )
                TextField(
                    value = port,
                    onValueChange = { port = it },
                    placeholder = {
                        Text("Port")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )
                TextField(
                    value = amount,
                    onValueChange = { amount = it },
                    placeholder = {
                        Text("Amount")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            LdkNode.openChannel(
                                pubkey,
                                "$ip:$port",
                                amount.toULong(),
                                0uL,
                                false
                            )
                        },
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                            .height(40.dp)
                            .width(150.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xff4f4f4f),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Open channel")
                    }
                }
            }
        }
    }
}
