package com.loki.glow.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.loki.glow.domain.model.CoinDetail

data class CoinX(
    @SerializedName("24hVolume")
    val volume: String,
    val allTimeHigh: AllTimeHigh,
    val btcPrice: String,
    val change: String,
    val coinrankingUrl: String,
    val color: String,
    val description: String,
    val fullyDilutedMarketCap: String,
    val iconUrl: String,
    val links: List<Link>,
    val listedAt: Int,
    val marketCap: String,
    val name: String,
    val numberOfExchanges: Int,
    val numberOfMarkets: Int,
    val price: String,
    val priceAt: Int,
    val rank: Int,
    val sparkline: List<String>,
    val supply: Supply,
    val symbol: String,
    val uuid: String,
    val websiteUrl: String
)

fun CoinX.toCoinDetail(): CoinDetail {

    return CoinDetail(
        coinId = uuid,
        cryptoImage = iconUrl,
        cryptoName = name,
        cryptoSymbol = symbol,
        cryptoAmount = price,
        cryptoChange = change,
        marketCap = marketCap,
        high = allTimeHigh.price,
        low = fullyDilutedMarketCap,
        volume = volume,
    )
}