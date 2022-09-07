package com.loki.glow.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.loki.glow.ui.crypto.BuyScreen
import com.loki.glow.ui.crypto.CryptoScreen
import com.loki.glow.ui.crypto.SellScreen
import com.loki.glow.ui.home.Crypto
import com.loki.glow.ui.home.Home
import com.loki.glow.ui.market.Market

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = Routes.HomeScreen.route
    ) {

        composable(route = Routes.HomeScreen.route) {
            Home(
                modifier = modifier,
                navController = navHostController
            )
        }

        composable(route = Routes.MarketScreen.route) {
            Market(modifier = modifier, navController = navHostController)
        }

        composable(route = Routes.WalletScreen.route) {

        }

        composable(route = Routes.HistoryScreen.route) {

        }

        composable(route = Routes.AccountScreen.route) {

        }

        composable(
            route = Routes.CryptoScreen.withArgs(),
            arguments = listOf(
                navArgument("cryptoName") {
                    type = NavType.StringType
                },
                navArgument("cryptoSymbol") {
                    type = NavType.StringType
                },
                navArgument("cryptoImage") {
                    type = NavType.IntType
                },
                navArgument("cryptoAmount") {
                    type = NavType.StringType
                },
                navArgument("cryptoChange") {
                    type = NavType.StringType
                },
                navArgument("marketCap") {
                    type = NavType.StringType
                },
                navArgument("high") {
                    type = NavType.StringType
                },
                navArgument("low") {
                    type = NavType.StringType
                },
                navArgument("volume") {
                    type = NavType.StringType
                }
            )
        ) {

            val cryptoImage = it.arguments?.getInt("cryptoImage")!!
            val cryptoName = it.arguments?.getString("cryptoName")!!
            val cryptoSymbol = it.arguments?.getString("cryptoSymbol")!!
            val cryptoAmount = it.arguments?.getString("cryptoAmount")!!
            val cryptoChange = it.arguments?.getString("cryptoChange")!!
            val marketCap = it.arguments?.getString("marketCap")!!
            val high = it.arguments?.getString("high")!!
            val low = it.arguments?.getString("low")!!
            val volume = it.arguments?.getString("volume")!!

            val crypto = Crypto(
                cryptoImage,
                cryptoName,
                cryptoSymbol,
                cryptoAmount,
                cryptoChange,
                marketCap,
                high,
                low,
                volume,
            )

            CryptoScreen(crypto = crypto, navController = navHostController)
        }

        composable(
            route = "${Routes.BuyScreen.route}/{cryptoName}",
            arguments = listOf(
                navArgument(name = "cryptoName") {
                    type = NavType.StringType
                }
            )
        ) {

            val cryptoName = it.arguments?.getString("cryptoName")!!
            BuyScreen(cryptoName = cryptoName)
        }

        composable(
            route = "${Routes.SellScreen.route}/{cryptoName}",
            arguments = listOf(
                navArgument(name = "cryptoName") {
                    type = NavType.StringType
                }
            )
        ) {

            val cryptoName = it.arguments?.getString("cryptoName")!!
            SellScreen(cryptoName = cryptoName)
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
    object BuyScreen: Routes("buy")
    object SellScreen: Routes("sell")

    fun withArgs(): String {
       return "${CryptoScreen.route}/{cryptoName}/{cryptoSymbol}/{cryptoImage}/{cryptoAmount}/{cryptoChange}/{marketCap}/{high}/{low}/{volume}"
    }

    fun navWithArgs(crypto: Crypto): String {
        return "${CryptoScreen.route}/${crypto.cryptoName}/${crypto.cryptoSymbol}/${crypto.cryptoImage}/${crypto.cryptoAmount}/${crypto.cryptoChange}/${crypto.marketCap}/${crypto.high}/${crypto.low}/${crypto.volume}"
    }
}