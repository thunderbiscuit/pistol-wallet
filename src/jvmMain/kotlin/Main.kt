package tb.sampleapps.pistolwallet

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import tb.sampleapps.pistolwallet.screens.BasePage

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Pistol Wallet",
        state = WindowState(width = 700.dp, height = 800.dp)
    ) {
        App()
    }
}

@Composable
fun App() {
    MaterialTheme(
        colors = lightColors(
            primary = Color(0xff26A69A),
            primaryVariant = Color(0xff5fcf95),
            // secondary = Color(0xFF009688)
        ),
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = {
            BasePage()
        }
    )
}
