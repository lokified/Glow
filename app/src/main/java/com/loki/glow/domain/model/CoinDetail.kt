package com.loki.glow.domain.model

data class CoinDetail (
    val coinId: String,
    val cryptoImage: String,
    val cryptoName: String,
    val cryptoSymbol: String,
    val cryptoAmount: String,
    val cryptoChange: String,
    val marketCap: String,
    val high: String,
    val low: String,
    val volume: String,
)

fun CoinDetail.toCoin(): Coin {

    return Coin(
        change = cryptoChange,
        name = cryptoName,
        symbol = cryptoSymbol,
        price = cryptoAmount,
        iconUrl = cryptoImage,
        uuid = coinId
    )
}