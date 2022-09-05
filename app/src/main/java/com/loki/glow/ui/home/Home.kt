package com.loki.glow.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.loki.glow.R
import com.loki.glow.ui.components.BottomNav


@Composable
fun Home(modifier: Modifier = Modifier) {

    Scaffold(
        topBar = { TopBar(name = "sheldon") },
        bottomBar = { BottomNav() }
    ) { padding ->

        Column(modifier = modifier.padding(padding)) {
            Wallet(balance = "2,000.23", modifier = Modifier.padding(16.dp))

            Sections(one = "Watchlist", two = "See all")

            WatchList()

            Sections(one = "Refer and earn", two = null)

            ReferCard(modifier = Modifier.padding(horizontal = 16.dp))

            Sections(one = "Top Trending", two = null)

            TopTrending()
        }
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier, name: String) {

    TopAppBar(
        modifier = modifier.height(70.dp),
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {

        Row(modifier = Modifier.padding(horizontal = 16.dp)) {

            Column {
                Text(text = "Hello $name")
                Text(text = "Welcome to Stock")
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
        backgroundColor = Color.Cyan,
        modifier = modifier
            .height(150.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        contentColor = Color.White
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text(text = "Wallet Balance (USD)")

            Text(text = "$${balance}", style = MaterialTheme.typography.h1)

            TextButton(
                onClick = { },
                modifier = Modifier.padding(top = 16.dp),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.Cyan,
                    backgroundColor = Color.White
                )
            ) {
                Text(text = "Add Funds")
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = null
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
        Text(text = two ?: "", color = Color.Green)
    }
}

@Composable
fun WatchList(modifier: Modifier = Modifier) {


    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(watchListData) { item ->

            WatchListCard(
                modifier = Modifier.height(110.dp),
                cryptoImage = item.cryptoImage,
                cryptoName = item.cryptoName,
                cryptoSymbol = item.cryptoSymbol,
                cryptoAmount = item.cryptoAmount,
                cryptoChange = item.cryptoChange!!
            )
        }
    }
}

val watchListData = listOf(
    DrawableStringPair(R.drawable.fc1_short_mantras , "Bitcoin" , "BTC" , "50102.20" , "1.40"),
    DrawableStringPair(R.drawable.fc2_nature_meditations,"Solana", "SOL", "23100.40", "2.75"),
    DrawableStringPair(R.drawable.fc3_stress_and_anxiety , "Ethereum", "ETH", "3100.90", "0.15"),
    DrawableStringPair(R.drawable.fc4_self_massage , "Tether USD", "Teth", "100.45", "3.89"),
    DrawableStringPair(R.drawable.fc5_overwhelmed , "Ape Coin", "APE", "40.40", "4.10"),
    DrawableStringPair(R.drawable.fc6_nightly_wind_down , "Avalanche", "AVAL", "45.40", "2.56")
)

data class DrawableStringPair(
    @DrawableRes val cryptoImage: Int,
    val cryptoName: String,
    val cryptoSymbol: String,
    val cryptoAmount: String,
    val cryptoChange: String
)

@Composable
fun WatchListCard(
    modifier: Modifier = Modifier,
    @DrawableRes cryptoImage: Int,
    cryptoName: String,
    cryptoSymbol: String,
    cryptoAmount: String,
    cryptoChange: String,
    positive: Boolean = true
) {
    val changeColor = Color.Green

    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colors.surface,
        modifier = modifier.clickable {  }
    ) {

        Column(modifier = Modifier
            .padding(8.dp)
            .width(190.dp)) {

            Row {

                Image(
                    painter = painterResource(id = cryptoImage),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = cryptoName,
                        style = MaterialTheme.typography.h2
                    )
                    Text(
                        text = cryptoSymbol,
                        style = MaterialTheme.typography.h3
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "$$cryptoAmount",
                style = MaterialTheme.typography.h2
            )
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .width(25.dp)
                    .padding(horizontal = 1.dp)
                    .background(
                        color = Color.Gray
                    )
            )
            Text(
                text = "$cryptoChange%",
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
        backgroundColor = Color.Cyan,
        contentColor = Color.White,
        shape = RoundedCornerShape(10.dp)
    ) {

        Row(modifier = Modifier.padding(12.dp)) {

            Image(
                painter = painterResource(id = R.drawable.ab1_inversions),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.End) {

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
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun TopTrending(modifier: Modifier = Modifier) {

    LazyRow(

        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(watchListData) { item ->

            TrendingCard(
                modifier = Modifier.height(65.dp),
                cryptoImage = item.cryptoImage,
                cryptoName = item.cryptoName,
                cryptoSymbol = item.cryptoSymbol
            )
        }
    }
}

@Composable
fun TrendingCard(
    modifier: Modifier = Modifier,
    @DrawableRes cryptoImage: Int,
    cryptoName: String,
    cryptoSymbol: String
) {

    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colors.surface,
        modifier = modifier
            .clickable { }
            .width(150.dp)
    ) {

        Row(modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = cryptoImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {

                Text(
                    text = cryptoName,
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = cryptoSymbol,
                    style = MaterialTheme.typography.h3
                )
            }
        }
    }
}