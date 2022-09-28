package com.loki.glow.data.remote

import com.loki.glow.data.remote.dto.CoinDetailDto
import com.loki.glow.data.remote.dto.CoinDto
import com.loki.glow.data.remote.dto.CoinRanking
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinRankingApi {

    @GET("coins")
    suspend fun getCoinList(): CoinRanking

    @GET("coin/{uuid}")
    suspend fun getCoinDetails(
        @Path("uuid") coinId: String
    ): CoinDetailDto
}