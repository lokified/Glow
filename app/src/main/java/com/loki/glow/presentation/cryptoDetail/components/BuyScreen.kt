package com.loki.glow.presentation.cryptoDetail.components

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.loki.glow.presentation.components.TransactScreen


@Composable
fun BuyScreen(modifier: Modifier = Modifier, cryptoName: String) {

    TransactScreen(
        modifier = modifier,
        cryptoName = "Buy $cryptoName",
        buttonColor = Color(0xFF518F88),
        actionText = "BUY"
    )
}
