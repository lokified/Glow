package com.loki.glow.data.remote.dto

import com.loki.glow.domain.model.Coin

data class CoinDto(
    val `24hVolume`: String,
    val btcPrice: String,
    val change: String,
    val coinrankingUrl: String,
    val color: String,
    val iconUrl: String,
    val listedAt: Int,
    val marketCap: String,
    val name: String,
    val price: String,
    val rank: Int,
    val sparkline: List<String>,
    val symbol: String,
    val uuid: String
)

fun CoinDto.toCoin(): Coin {

    return Coin(
        name = name,
        change = change,
        iconUrl = iconUrl,
        uuid = uuid,
        price = price,
        symbol = symbol
    )
}