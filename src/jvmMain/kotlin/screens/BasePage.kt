package tb.sampleapps.pistolwallet.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BasePage() {
    val currentPage = remember { mutableStateOf(PageState.HOME) }

    Sidebar(currentPage)
    Page(currentPage.value)
}

@Composable
fun Sidebar(currentPage: MutableState<PageState>) {
    Column (
        Modifier
            .fillMaxHeight()
            .width(140.dp)
            .background(Color(0xFF2D2D2D))
    ){
        Button(
            onClick = { currentPage.value = PageState.HOME },
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                .height(80.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xff4f4f4f),
                contentColor = Color.White
            )
        ) {
            Text("Wallet")
        }
        Button(
            onClick = { currentPage.value = PageState.NODE },
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                .height(80.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xff4f4f4f),
                contentColor = Color.White
            )
        ) {
            Text("Dashboard")
        }
        Button(
            onClick = { currentPage.value = PageState.PEERS },
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                .height(80.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xff4f4f4f),
                contentColor = Color.White
            )
        ) {
            Text("Peers")
        }
    }
}

@Composable
fun Page(currentPage: PageState) {
    when (currentPage) {
        PageState.HOME  -> Wallet()
        PageState.NODE  -> Dashboard()
        PageState.PEERS -> Peers()
    }
}

@Composable
fun Title(title: String) {
    Column(
        modifier = Modifier.height(80.dp).fillMaxWidth().padding(start = 170.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

enum class PageState {
    HOME,
    NODE,
    PEERS
}
