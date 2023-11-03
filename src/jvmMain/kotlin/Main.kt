package tb.sampleapps.pistolwallet

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import tb.sampleapps.pistolwallet.screens.BasePage

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Pistol Wallet",
        state = WindowState(width = 650.dp, height = 800.dp)
    ) {
        App()
    }
}

@Composable
fun App() {
    MaterialTheme {
        BasePage()
    }
}
