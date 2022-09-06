package com.loki.glow.ui.market

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loki.glow.ui.components.TopBar
import com.loki.glow.ui.home.Crypto
import com.loki.glow.ui.home.watchListData
import com.loki.glow.ui.theme.GlowTheme

@Composable
fun Market(modifier: Modifier = Modifier) {

    Scaffold(topBar = { TopBar(title = "Cryptos") }) {

        CryptoSection()
    }
}


@Composable
fun Search() {


}

@Composable
fun CryptoSection() {

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {

        items(watchListData) { item ->

            CryptoCard(
                crypto = item
            )
        }
    }
}

@Composable
fun CryptoCard(
    modifier: Modifier = Modifier,
    crypto: Crypto,
    color: Color = MaterialTheme.colors.surface
) {

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = color
    ) {

        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {

            Image(
                painter = painterResource(id = crypto.cryptoImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
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

            Spacer(modifier = Modifier.weight(1f))

            Column {

                Text(
                    text = "$${crypto.cryptoAmount}",
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = "${crypto.cryptoChange}%",
                    style = MaterialTheme.typography.h3,
                    color = Color.Green
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {

    GlowTheme {
       Market()
    }
}