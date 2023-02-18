package tb.sampleapps.pistolwallet

import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import tb.sampleapps.pistolwallet.screens.BasePage

@Composable
@Preview
fun App() {
    MaterialTheme {
        BasePage()
    }
}

fun main() = application {
    // LdkNode.initialize()

    Window(
        title = "Pistol Wallet",
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
