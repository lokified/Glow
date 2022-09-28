package com.loki.glow.domain.repository

import com.loki.glow.data.remote.dto.CoinDetailDto
import com.loki.glow.data.remote.dto.CoinDto
import com.loki.glow.data.remote.dto.CoinRanking

interface CoinRepository {

    suspend fun getCoinList(): CoinRanking
    suspend fun getCoinDetail(coinId: String): CoinDetailDto
}