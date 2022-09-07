package com.loki.glow.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNav(
    modifier: Modifier = Modifier,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
) {

    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.White
    ) {

        navItems.forEach { bottomNavItem ->

            val selected = bottomNavItem.route == backStackEntry.value?.destination?.route

            BottomNavigationItem(
                selected = selected,
                selectedContentColor = Color.Cyan,
                unselectedContentColor = Color.Gray,
                onClick = { onItemClick(bottomNavItem) },
                icon = {
                    Icon(
                        imageVector = bottomNavItem.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = bottomNavItem.navTitle)
                }
            )

        }

    }
}


 data class BottomNavItem(
    val icon: ImageVector,
    val navTitle: String,
    val route: String
)


val navItems = listOf(

    BottomNavItem(
        icon = Icons.Filled.Home,
        navTitle = "Home",
        route = Routes.HomeScreen.route
    ),
    BottomNavItem(
        icon = Icons.Default.TrendingUp,
        navTitle = "Market",
        route = Routes.MarketScreen.route
    ),
    BottomNavItem(
        icon = Icons.Default.Money,
        navTitle = "Wallet",
        route = Routes.WalletScreen.route
    ),
    BottomNavItem(
        icon = Icons.Default.History,
        navTitle = "History",
        route = Routes.HistoryScreen.route
    ),
    BottomNavItem(
        icon = Icons.Filled.AccountCircle,
        navTitle = "Account",
        route = Routes.AccountScreen.route
    )
)