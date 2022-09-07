package com.loki.glow.ui.crypto

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.loki.glow.ui.components.TransactScreen


@Composable
fun BuyScreen(modifier: Modifier = Modifier, cryptoName: String) {

    TransactScreen(
        modifier = modifier,
        cryptoName = "Buy $cryptoName",
        buttonColor = Color(0xFF518F88),
        actionText = "BUY"
    )
}
