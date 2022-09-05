package com.loki.glow.ui.crypto

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.loki.glow.R
import com.loki.glow.ui.market.CryptoCard
import com.loki.glow.ui.theme.GlowTheme

@Composable
fun CryptoScreen(
    cryptoImage: Int ,
    cryptoName: String ,
    cryptoSymbol: String ,
    cryptoAmount: String,
    cryptoChange: String
) {

    CryptoCard(
        cryptoImage = cryptoImage,
        cryptoName = cryptoName,
        cryptoSymbol = cryptoSymbol,
        cryptoAmount = cryptoAmount,
        cryptoChange = cryptoChange
    )
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {

    GlowTheme {
        CryptoScreen(
            cryptoImage =  R.drawable.ab2_quick_yoga,
            cryptoName = "Bitcoin",
            cryptoSymbol = "BTC",
            cryptoAmount = "10,000",
            cryptoChange = "4.89"
        )
    }
}