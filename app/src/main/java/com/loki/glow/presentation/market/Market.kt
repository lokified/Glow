package com.loki.glow.presentation.market

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.loki.glow.domain.model.Coin
import com.loki.glow.presentation.components.CoinIcon
import com.loki.glow.presentation.components.DetailsScreen
import com.loki.glow.presentation.components.TopBar
import com.loki.glow.presentation.home.HomeViewModel

@Composable
fun Market(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val state = homeViewModel.state.value

    Scaffold(topBar = { TopBar(title = "Cryptos") }) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            Column(modifier = modifier.padding(padding)) {

                Search()

                CryptoSection(navController, coinList = state.coins)
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colors.primary
                )
            }

            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}


@Composable
fun Search() {

    var term by remember { mutableStateOf("") }

    OutlinedTextField(
        value = term,
        onValueChange = { term = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text(text = "Search")
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = MaterialTheme.colors.primary,
            cursorColor = MaterialTheme.colors.primary
        )
    )

}

@Composable
fun CryptoSection(
    navController: NavController,
    coinList: List<Coin>
) {

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {

        items(coinList) { item ->

            CryptoCard(
                coin = item,
                onClick = { coin ->
                    navController.navigate(DetailsScreen.CryptoScreen.navWithArgs(coin.uuid))
                }
            )
        }
    }
}

@Composable
fun CryptoCard(
    modifier: Modifier = Modifier,
    coin: Coin,
    color: Color = MaterialTheme.colors.surface,
    onClick: (Coin) -> Unit = { }
) {

    val changeColor = if(!coin.change.contains("-")) Color(0xFF2CAD58) else Color.Red
    val coinPrice = String.format("%.2f", coin.price.toDouble())
    val iconTrend = if (!coin.change.contains("-"))Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown
    val iconTrendColor = if (!coin.change.contains("-"))Color(0xFF2CAD58) else Color.Red

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(coin)
            },
        color = color
    ) {

        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            CoinIcon(coinUrl = coin.iconUrl)
            Spacer(modifier = Modifier.width(8.dp))

            Column {

                Text(
                    text = coin.name,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.width(150.dp),
                    softWrap = true
                )
                Text(
                    text = coin.symbol,
                    style = MaterialTheme.typography.h3
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    text = "$${coinPrice}",
                    style = MaterialTheme.typography.h2
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${coin.change}%",
                        style = MaterialTheme.typography.h3,
                        color = changeColor
                    )
                    Icon(
                        imageVector = iconTrend,
                        contentDescription = null,
                        tint = iconTrendColor
                    )
                }

            }

        }
    }
}