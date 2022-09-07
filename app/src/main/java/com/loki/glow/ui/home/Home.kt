package com.loki.glow.ui.home

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.loki.glow.R
import com.loki.glow.ui.components.BottomNav
import com.loki.glow.ui.components.Routes
import kotlinx.parcelize.Parcelize


@Composable
fun Home(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    Scaffold(
        topBar = { TopBar(name = "sheldon") }
    ) {

        Column(modifier = modifier.verticalScroll(rememberScrollState())) {
            Wallet(balance = "2,000.23", modifier = Modifier.padding(16.dp))

            Sections(one = "Watchlist", two = "See all")

            WatchList(navController = navController)

            Sections(one = "Refer and earn", two = null)

            ReferCard(modifier = Modifier.padding(horizontal = 16.dp))

            Sections(one = "Top Trending", two = null)

            TopTrending(navController = navController)
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
        backgroundColor = Color(0xFF518F88),
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
                    contentColor = Color(0xFF518F88),
                    backgroundColor = Color.White
                )
            ) {
                Text(text = "Add Funds")
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        color = Color(0xFF518F88)
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
        Text(text = two ?: "", color = Color(0xFF518F88))
    }
}

@Composable
fun WatchList(
    modifier: Modifier = Modifier,
    navController: NavController
) {


    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(watchListData) { item ->

            WatchListCard(
                modifier = Modifier.height(110.dp),
                crypto = item,
                navController = navController
            )
        }
    }
}

@Parcelize
data class Crypto(
    @DrawableRes val cryptoImage: Int,
    val cryptoName: String,
    val cryptoSymbol: String,
    val cryptoAmount: String,
    val cryptoChange: String,
    val marketCap: String,
    val high: String,
    val low: String,
    val volume: String
): Parcelable

val watchListData = listOf(
    Crypto(R.drawable.fc1_short_mantras , "Bitcoin" , "BTC" , "50102.20" , "1.40", "108.51", "49.96", "15.6", "50.63"),
    Crypto(R.drawable.fc2_nature_meditations,"Solana", "SOL", "23100.40", "2.75", "108.51", "49.96", "15.6", "50.63"),
    Crypto(R.drawable.fc3_stress_and_anxiety , "Ethereum", "ETH", "3100.90", "0.15", "108.51", "49.96", "15.6", "50.63"),
    Crypto(R.drawable.fc4_self_massage , "Tether USD", "Teth", "100.45", "3.89", "108.51", "49.96", "15.6", "50.63"),
    Crypto(R.drawable.fc5_overwhelmed , "Ape Coin", "APE", "40.40", "4.10", "108.51", "49.96", "15.6", "50.63"),
    Crypto(R.drawable.fc6_nightly_wind_down , "Avalanche", "AVAL", "45.40", "2.56", "108.51", "49.96", "15.6", "50.63"),

    Crypto(R.drawable.fc1_short_mantras , "Bitcoin" , "BTC" , "50102.20" , "1.40", "108.51", "49.96", "15.6", "50.63"),
    Crypto(R.drawable.fc2_nature_meditations,"Solana", "SOL", "23100.40", "2.75", "108.51", "49.96", "15.6", "50.63"),
    Crypto(R.drawable.fc3_stress_and_anxiety , "Ethereum", "ETH", "3100.90", "0.15", "108.51", "49.96", "15.6", "50.63"),
    Crypto(R.drawable.fc4_self_massage , "Tether USD", "Teth", "100.45", "3.89", "108.51", "49.96", "15.6", "50.63"),
    Crypto(R.drawable.fc5_overwhelmed , "Ape Coin", "APE", "40.40", "4.10", "108.51", "49.96", "15.6", "50.63"),
    Crypto(R.drawable.fc6_nightly_wind_down , "Avalanche", "AVAL", "45.40", "2.56", "108.51", "49.96", "15.6", "50.63")
)

@Composable
fun WatchListCard(
    modifier: Modifier = Modifier,
    crypto: Crypto,
    onItemClick: (Crypto) -> Unit = { },
    navController: NavController
) {
    val changeColor = Color(0xFF2CAD58)

    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colors.surface,
        modifier = modifier.clickable {
            onItemClick(crypto)
            navController.navigate(Routes.CryptoScreen.navWithArgs(crypto))
        }
    ) {

        Column(modifier = Modifier
            .padding(8.dp)
            .width(190.dp)) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = crypto.cryptoImage),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = crypto.cryptoName,
                        style = MaterialTheme.typography.h2
                    )
                    Text(
                        text = crypto.cryptoSymbol,
                        style = MaterialTheme.typography.h3
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "$${crypto.cryptoAmount}",
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
                text = "${crypto.cryptoChange}%",
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
        backgroundColor =Color(0xFF518F88),
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
fun TopTrending(modifier: Modifier = Modifier, navController: NavController) {

    LazyRow(

        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(watchListData) { item ->

            TrendingCard(
                modifier = Modifier.height(65.dp),
                crypto = item,
                navController = navController
            )
        }
    }
}

@Composable
fun TrendingCard(
    modifier: Modifier = Modifier,
    crypto: Crypto,
    navController: NavController
) {

    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colors.surface,
        modifier = modifier
            .clickable {
                navController.navigate(Routes.CryptoScreen.navWithArgs(crypto))
            }
            .width(150.dp)
    ) {

        Row(modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = crypto.cryptoImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {

                Text(
                    text = crypto.cryptoName,
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = crypto.cryptoSymbol,
                    style = MaterialTheme.typography.h3
                )
            }
        }
    }
}