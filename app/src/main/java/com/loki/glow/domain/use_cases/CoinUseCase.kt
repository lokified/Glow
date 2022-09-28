package com.loki.glow.domain.use_cases

import com.loki.glow.domain.use_cases.get_coin.GetCoinUseCase
import com.loki.glow.domain.use_cases.get_coins.GetCoinsUseCase

data class CoinUseCase (
    val getCoinList: GetCoinsUseCase,
    val getCoin: GetCoinUseCase
    )