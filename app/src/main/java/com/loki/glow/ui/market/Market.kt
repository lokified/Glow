package com.loki.glow.ui.market

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.loki.glow.ui.components.Routes
import com.loki.glow.ui.components.TopBar
import com.loki.glow.ui.crypto.CryptoScreen
import com.loki.glow.ui.home.Crypto
import com.loki.glow.ui.home.watchListData
import com.loki.glow.ui.theme.GlowTheme

@Composable
fun Market(modifier: Modifier = Modifier, navController: NavController) {

    Scaffold(topBar = { TopBar(title = "Cryptos") }) { padding ->

        Column(modifier = modifier.padding(padding)) {

            Search()
            
            CryptoSection(navController)
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
            focusedIndicatorColor = Color(0xFF518F88),
            cursorColor = Color(0xFF518F88)
        )
    )

}

@Composable
fun CryptoSection(navController: NavController) {

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {

        items(watchListData) { item ->

            CryptoCard(
                crypto = item,
                navController = navController
            )
        }
    }
}

@Composable
fun CryptoCard(
    modifier: Modifier = Modifier,
    crypto: Crypto,
    color: Color = MaterialTheme.colors.surface,
    navController: NavController,
    onClick: (Crypto) -> Unit = { }
) {

    Surface(
        modifier = modifier.fillMaxWidth().clickable {
            onClick(crypto)
            navController.navigate(Routes.CryptoScreen.navWithArgs(crypto))
        },
        color = color
    ) {

        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
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