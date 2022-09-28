package com.loki.glow.presentation.cryptoDetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.loki.glow.R
import com.loki.glow.domain.model.CoinDetail
import com.loki.glow.domain.model.toCoin
import com.loki.glow.presentation.components.DetailsScreen
import com.loki.glow.presentation.cryptoDetail.CryptoDetailViewModel
import com.loki.glow.presentation.market.CryptoCard

@Composable
fun CryptoScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    coinDetailViewModel: CryptoDetailViewModel = hiltViewModel()
) {

    val state = coinDetailViewModel.state.value

    Scaffold(topBar = { CryptoAppBar() }) { padding ->

        Column(modifier = modifier) {

            state.coin?.let { coin ->
                CryptoCard(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    coin = coin.toCoin(),
                    color = Color.White,
                    onClick = { coin_d ->
                        navController.navigate(DetailsScreen.CryptoScreen.navWithArgs(coin_d.uuid))
                    }
                )

                OverviewCard(
                    modifier = Modifier.padding(16.dp),
                    coin = coin
                )

                ActionButton(
                    coin = coin,
                    onSellClick = { coin_s ->
                        navController.navigate("${DetailsScreen.SellScreen.route}/${coin_s.cryptoName}")
                    },
                    onBuyClick = { coin_b ->
                        navController.navigate("${DetailsScreen.BuyScreen.route}/${coin_b.cryptoName}")
                    }
                )
            }

        }

    }

}


@Composable
fun CryptoAppBar(modifier: Modifier = Modifier) {

    TopAppBar(
        modifier = modifier.height(70.dp),
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {

        Row(modifier = Modifier.padding(horizontal = 16.dp)) {

            Surface(
                modifier = Modifier.clickable {  },
                shape = RoundedCornerShape(30.dp)
            ) {

                Image(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.padding(5.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Surface(
                modifier = Modifier.clickable {  },
                shape = RoundedCornerShape(30.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.outline_favorite),
                    contentDescription = null,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
}

@Composable
fun OverviewCard(
    modifier: Modifier = Modifier,
    coin: CoinDetail
) {

    Card(modifier = modifier) {

        Column(modifier = Modifier.padding(12.dp)) {

            Text(text = "Overview", style = MaterialTheme.typography.h4)

            Column {

                OverViewRow(name = "Market Cap", amount = coin.marketCap)
                OverViewRow(name = "High", amount = coin.high)
                OverViewRow(name = "Low", amount = coin.low)
                OverViewRow(name = "Volume", amount = coin.volume)
            }
        }

    }

}


@Composable
fun OverViewRow(name: String, amount: String) {

    Row(Modifier.padding(vertical = 8.dp)) {

        Text(text = name, style = MaterialTheme.typography.h3)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = amount, style = MaterialTheme.typography.h5)
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    coin: CoinDetail,
    onSellClick: (CoinDetail) -> Unit,
    onBuyClick: (CoinDetail) -> Unit
) {

    Row(modifier = modifier.padding(16.dp)) {

        Button(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(end = 4.dp),
            onClick = { onBuyClick(coin) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF518F88)
            )
        ) {
            
            Text(text = "BUY", color = Color.White)
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(start = 4.dp),
            onClick = { onSellClick(coin) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFF9800)
            )
        ) {

            Text(text = "SELL", color = Color.White)
        }
    }
}