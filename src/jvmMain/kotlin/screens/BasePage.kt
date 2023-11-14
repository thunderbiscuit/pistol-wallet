package tb.sampleapps.pistolwallet.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource

@Composable
fun BasePage() {
    val currentPage = remember { mutableStateOf(PageState.DASHBOARD) }

    val items = listOf(
        NavigationItem(
            title = "Dashboard",
            icon = Icons.Outlined.Home,
        ),
        NavigationItem(
            title = "Channels",
            icon = Icons.Outlined.AccountCircle,
        ),
    )

    Row {
        NavigationSideBar(
            items = items,
            selectedItemIndex = currentPage.value.ordinal,
            onNavigate = { currentPage.value = it }
        )
        Page(currentPage.value)
    }
}

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
)

@Composable
fun NavigationSideBar(
    items: List<NavigationItem>,
    selectedItemIndex: Int,
    onNavigate: (PageState) -> Unit
) {
    NavigationRail(
        header = {
            FloatingActionButton(
                modifier = Modifier.padding(top = 8.dp),
                onClick = { onNavigate(PageState.LNWALLET) },
            ) {
                val image = painterResource("icons/flash_on.svg")
                Image(
                    painter = image,
                    contentDescription = "Payment"
                )
            }
        },
        modifier = Modifier.width(100.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Bottom)
        ) {
            items.forEachIndexed { index, item ->
                val pageState = when (index) {
                    0 -> PageState.DASHBOARD
                    1 -> PageState.CHANNELS
                    else -> throw IllegalArgumentException("Unknown index: $index")
                }

                NavigationRailItem(
                    selected = selectedItemIndex == index,
                    onClick = { onNavigate(pageState) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(text = item.title) },
                )
            }
        }
    }
}

@Composable
fun Page(currentPage: PageState) {
    when (currentPage) {
        PageState.LNWALLET  -> LnWallet()
        PageState.DASHBOARD -> Dashboard()
        PageState.CHANNELS  -> Channels()
    }
}

@Composable
fun Title(title: String) {
    Column(
        modifier = Modifier.height(80.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

enum class PageState {
    DASHBOARD,
    CHANNELS,
    LNWALLET,
}
