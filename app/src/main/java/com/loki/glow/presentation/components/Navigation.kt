package com.loki.glow.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.loki.glow.presentation.cryptoDetail.components.BuyScreen
import com.loki.glow.presentation.cryptoDetail.components.CryptoScreen
import com.loki.glow.presentation.cryptoDetail.components.SellScreen
import com.loki.glow.presentation.home.components.Home
import com.loki.glow.presentation.market.Market
import com.loki.glow.util.Constants

@Composable
fun HomeNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = HomeRoutes.HomeScreen.route
    ) {

        composable(route = HomeRoutes.HomeScreen.route) {
            Home(
                modifier = modifier,
                navController = navHostController
            )
        }

        composable(route = HomeRoutes.MarketScreen.route) {
            Market(modifier = modifier, navController = navHostController)
        }

        composable(route = HomeRoutes.WalletScreen.route) {

        }

        composable(route = HomeRoutes.HistoryScreen.route) {

        }

        composable(route = HomeRoutes.AccountScreen.route) {

        }

        detailsNavGraph(navHostController)
    }
}

sealed class HomeRoutes(val route: String) {

    object HomeScreen: HomeRoutes("home")
    object WalletScreen: HomeRoutes("wallet")
    object MarketScreen: HomeRoutes("market")
    object HistoryScreen: HomeRoutes("history")
    object AccountScreen: HomeRoutes("account")
}

fun NavGraphBuilder.detailsNavGraph(navController: NavController) {

    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.CryptoScreen.route
    ) {

        composable(
            route = DetailsScreen.CryptoScreen.withArgs(),
            arguments = listOf(
                navArgument(Constants.PARAM_COIN_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            CryptoScreen(navController = navController)
        }

        composable(
            route = "${DetailsScreen.BuyScreen.route}/{cryptoName}",
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
            route = "${DetailsScreen.SellScreen.route}/{cryptoName}",
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

sealed class DetailsScreen(val route: String) {

    object CryptoScreen: DetailsScreen("crypto")
    object BuyScreen: DetailsScreen("buy")
    object SellScreen: DetailsScreen("sell")

    fun withArgs(): String {
        return "${CryptoScreen.route}/{coinId}"
    }

    fun navWithArgs(coinId: String): String {
        return "${CryptoScreen.route}/${coinId}"
    }
}

object Graph {
    const val DETAILS = "details_graph"
}