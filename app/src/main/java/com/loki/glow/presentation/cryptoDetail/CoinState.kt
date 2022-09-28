package com.loki.glow.presentation.cryptoDetail

import com.loki.glow.domain.model.CoinDetail

data class CoinState(
    val error: String = "",
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null
)
