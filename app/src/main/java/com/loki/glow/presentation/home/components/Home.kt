package com.loki.glow.presentation.home.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.loki.glow.domain.model.Coin
import com.loki.glow.presentation.components.CoinIcon
import com.loki.glow.presentation.components.DetailsScreen
import com.loki.glow.presentation.home.HomeViewModel


@Composable
fun Home(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val state = homeViewModel.state.value

    Scaffold(
        topBar = { TopBar(name = "sheldon") }
    ) { padding ->

        Column(modifier = modifier.verticalScroll(rememberScrollState())) {
            Wallet(balance = "2,000.23", modifier = Modifier.padding(16.dp))

            Sections(one = "Watchlist", two = "See all")

            WatchList(navController = navController, coinList = state.coins)

            Sections(one = "Refer and earn", two = null)

            ReferCard(modifier = Modifier.padding(horizontal = 16.dp))

            Sections(one = "Top Trending", two = null)

            TopTrending(navController = navController, coinList = state.coins)
        }
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    name: String
) {

    TopAppBar(
        modifier = modifier.height(70.dp),
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {

        Row(modifier = Modifier.padding(horizontal = 16.dp)) {

            Column {
                Text(text = "Hello $name", style = MaterialTheme.typography.h1)
                Text(text = "Welcome to Stock", style = MaterialTheme.typography.body1)
            }

            Spacer(modifier = Modifier.weight(1f))

            val padding = Modifier.padding(10.dp)

            Surface(
                color = MaterialTheme.colors.surface,
                modifier = Modifier.clickable {  },
                shape = RoundedCornerShape(30.dp)
            ) {
                Image(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    modifier = padding
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Surface(
                color = MaterialTheme.colors.surface,
                modifier = Modifier.clickable {  },
                shape = RoundedCornerShape(30.dp)
            ) {
                Image(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = null,
                    modifier = padding
                )
            }

        }
    }
}


@Composable
fun Wallet(
    modifier: Modifier = Modifier,
    balance: String
) {

    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = modifier
            .height(150.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        contentColor = Color.White
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text(text = "Wallet Balance (USD)")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$${balance}", style = MaterialTheme.typography.h1)

            TextButton(
                onClick = { },
                modifier = Modifier.padding(top = 30.dp),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colors.primary,
                    backgroundColor = Color.White
                )
            ) {
                Text(text = "Add Funds")
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        color = MaterialTheme.colors.primary
                    )
                )
            }
        }

    }
}

@Composable
fun Sections(one: String, two: String?) {

    Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {

        Text(text = one)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = two ?: "", color = MaterialTheme.colors.primary)
    }
}

@Composable
fun WatchList(
    modifier: Modifier = Modifier,
    navController: NavController,
    coinList: List<Coin>
) {


    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(coinList) { item ->

            WatchListCard(
                modifier = Modifier.height(110.dp),
                coin = item,
                onItemClick = { coin ->
                    navController.navigate(DetailsScreen.CryptoScreen.navWithArgs(coin.uuid))
                }
            )
        }
    }
}


@Composable
fun WatchListCard(
    modifier: Modifier = Modifier,
    coin: Coin,
    onItemClick: (Coin) -> Unit = { }
) {
    val changeColor = if(!coin.change.contains("-")) Color(0xFF2CAD58) else Color.Red
    val coinPrice = String.format("%.2f", coin.price.toDouble())
    val iconTrend = if (!coin.change.contains("-"))Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown
    val iconTrendColor = if (!coin.change.contains("-"))Color(0xFF2CAD58) else Color.Red

    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colors.surface,
        modifier = modifier.clickable {
            onItemClick(coin)
        }
    ) {

        Column(modifier = Modifier
            .padding(8.dp)
            .width(190.dp)) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                CoinIcon(coinUrl = coin.iconUrl)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = coin.name,
                        style = MaterialTheme.typography.h2
                    )
                    Text(
                        text = coin.symbol,
                        style = MaterialTheme.typography.h3
                    )
                }
                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = iconTrend,
                    contentDescription = null,
                    tint = iconTrendColor
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "$${coinPrice}",
                style = MaterialTheme.typography.h2
            )
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .width(25.dp)
                    .padding(horizontal = 1.dp, vertical = 4.dp)
                    .background(
                        color = Color.Gray
                    )
            )
            Text(
                text = "${coin.change}%",
                style = MaterialTheme.typography.h3,
                color = changeColor
            )
        }
    }
}

@Composable
fun ReferCard(modifier: Modifier = Modifier) {

    Card(modifier = modifier
        .height(115.dp)
        .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        shape = RoundedCornerShape(10.dp)
    ) {

        Row(modifier = Modifier.padding(12.dp)) {


            Spacer(modifier = Modifier.weight(1f))
            Column(modifier = Modifier.padding(end = 30.dp)) {

                Text(text = "Earn up to $500 \nper referral")
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(
                    onClick = { },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.White
                    )
                ) {
                    
                    Text(text = "Send Invite")
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun TopTrending(
    modifier: Modifier = Modifier,
    navController: NavController,
    coinList: List<Coin>
) {

    LazyRow(

        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(coinList) { item ->

            TrendingCard(
                modifier = Modifier.height(65.dp),
                coin = item,
                onItemClick = { coin ->
                    navController.navigate(DetailsScreen.CryptoScreen.navWithArgs(coin.uuid))
                }
            )
        }
    }
}

@Composable
fun TrendingCard(
    modifier: Modifier = Modifier,
    coin: Coin,
    onItemClick: (Coin) -> Unit
) {

    val changeColor = if(!coin.change.contains("-")) Color(0xFF2CAD58) else Color.Red


    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colors.surface,
        modifier = modifier
            .clickable {
                onItemClick(coin)
            }
            .width(150.dp)
    ) {

        Row(modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            CoinIcon(coinUrl = coin.iconUrl)
            Spacer(modifier = Modifier.width(8.dp))
            Column {

                Text(
                    text = coin.name,
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = "${coin.change}%",
                    style = MaterialTheme.typography.h3,
                    color = changeColor
                )
            }
        }
    }
}