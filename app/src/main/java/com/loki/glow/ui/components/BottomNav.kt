package com.loki.glow.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomNav() {

    BottomNavigation(
        backgroundColor = Color.White
    ) {

        navItems.forEach { bottomNavItem ->

            BottomNavigationItem(
                selected = bottomNavItem.selected,
                selectedContentColor = Color.Cyan,
                unselectedContentColor = Color.Gray,
                onClick = {  },
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
    val selected: Boolean
)


val navItems = listOf(

    BottomNavItem(
        icon = Icons.Filled.Home,
        navTitle = "Home",
        selected = true
    ),
    BottomNavItem(
        icon = Icons.Default.Star,
        navTitle = "Market",
        selected = false
    ),
    BottomNavItem(
        icon = Icons.Default.Lock,
        navTitle = "Wallet",
        selected = false
    ),
    BottomNavItem(
        icon = Icons.Default.Build,
        navTitle = "History",
        selected = false
    ),
    BottomNavItem(
        icon = Icons.Filled.AccountCircle,
        navTitle = "Account",
        selected = false
    )
)