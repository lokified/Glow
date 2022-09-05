package com.loki.glow.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.loki.glow.ui.home.Home
import com.loki.glow.ui.market.Market

@Composable
fun Navigation(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = Routes.HomeScreen.route
    ) {

        composable(route = Routes.HomeScreen.route) {
            Home()
        }

        composable(route = Routes.MarketScreen.route) {
            Market()
        }

        composable(route = Routes.WalletScreen.route) {

        }

        composable(route = Routes.HistoryScreen.route) {

        }

        composable(route = Routes.AccountScreen.route) {

        }

        composable(route = Routes.CryptoScreen.route) {

        }
    }
}

sealed class Routes(val route: String) {

    object HomeScreen: Routes("home")
    object WalletScreen: Routes("wallet")
    object MarketScreen: Routes("market")
    object HistoryScreen: Routes("history")
    object AccountScreen: Routes("account")
    object CryptoScreen: Routes("crypto")
}