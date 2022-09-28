package com.loki.glow.presentation.cryptoDetail.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.loki.glow.presentation.components.TransactScreen

@Composable
fun SellScreen(modifier: Modifier = Modifier, cryptoName: String) {

    TransactScreen(
        modifier = modifier,
        cryptoName = "Sell $cryptoName",
        buttonColor = Color(0xFFFF9800),
        actionText = "SELL"
    )
}