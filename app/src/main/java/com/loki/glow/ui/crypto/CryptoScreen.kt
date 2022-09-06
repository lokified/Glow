package com.loki.glow.ui.crypto

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loki.glow.R
import com.loki.glow.ui.home.Crypto
import com.loki.glow.ui.market.CryptoCard
import com.loki.glow.ui.theme.GlowTheme

@Composable
fun CryptoScreen(
    modifier: Modifier = Modifier,
    crypto: Crypto
) {

    Scaffold(topBar = { CryptoAppBar() }) {

        Column(modifier = modifier) {
            CryptoCard(
                modifier = Modifier.padding(horizontal = 16.dp),
                crypto = crypto,
                color = Color.White
            )
            
            OverviewCard(
                modifier = Modifier.padding(16.dp),
                crypto = crypto
            )
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
                    imageVector = Icons.Filled.Favorite,
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
    crypto: Crypto
) {

    Card(modifier = modifier) {

        Column(modifier = Modifier.padding(12.dp)) {

            Text(text = "Overview", style = MaterialTheme.typography.h4)

            Column {

                OverViewRow(name = "Market Cap", amount = crypto.marketCap)
                OverViewRow(name = "High", amount = crypto.high)
                OverViewRow(name = "Low", amount = crypto.low)
                OverViewRow(name = "Volume", amount = crypto.volume)
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

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {

    GlowTheme {
        CryptoScreen(
            crypto = Crypto(R.drawable.fc1_short_mantras , "Bitcoin" , "BTC" , "50102.20" , "1.40", "108.51", "49.96", "15.6", "50.63")

            )
    }
}