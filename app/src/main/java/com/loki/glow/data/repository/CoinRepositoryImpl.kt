package com.loki.glow.data.repository

import com.loki.glow.data.remote.CoinRankingApi
import com.loki.glow.data.remote.dto.CoinDetailDto
import com.loki.glow.data.remote.dto.CoinDto
import com.loki.glow.data.remote.dto.CoinRanking
import com.loki.glow.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinRankingApi
): CoinRepository {

    override suspend fun getCoinList(): CoinRanking {
        return api.getCoinList()
    }

    override suspend fun getCoinDetail(coinId: String): CoinDetailDto {
        return api.getCoinDetails(coinId)
    }
}