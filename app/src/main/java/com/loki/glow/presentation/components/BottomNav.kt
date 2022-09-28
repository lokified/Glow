package com.loki.glow.presentation.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val bottomBarDestination = navItems.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomNavigation(
            modifier = modifier,
            backgroundColor = Color.White
        ) {

            navItems.forEach { bottomNavItem ->

                val selected = bottomNavItem.route == backStackEntry?.destination?.route

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
        route = HomeRoutes.HomeScreen.route
    ),
    BottomNavItem(
        icon = Icons.Default.TrendingUp,
        navTitle = "Market",
        route = HomeRoutes.MarketScreen.route
    ),
    BottomNavItem(
        icon = Icons.Default.Money,
        navTitle = "Wallet",
        route = HomeRoutes.WalletScreen.route
    ),
    BottomNavItem(
        icon = Icons.Default.History,
        navTitle = "History",
        route = HomeRoutes.HistoryScreen.route
    ),
    BottomNavItem(
        icon = Icons.Filled.AccountCircle,
        navTitle = "Account",
        route = HomeRoutes.AccountScreen.route
    )
)