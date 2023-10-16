package com.tariq.cryptotracker.data.remote.dto


import com.tariq.cryptotracker.domain.model.CoinDetail

data class CoinDetailDto(
    val id: String,
    val icon: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val price: Double,
    val priceBtc: Double,
    val volume: Double,
    val marketCap: Double,
    val availableSupply: Long,
    val totalSupply: Long,
    val priceChange1h: Double,
    val priceChange1d: Double,
    val priceChange1w: Double,
    val redditUrl: String,
    val websiteUrl: String,
    val twitterUrl: String,
    val explorers: List<String>
)

fun CoinDetailDto.toCoinDetail() : CoinDetail {
    return CoinDetail(
        id=id,
        icon=icon,
        name=name,
        symbol=symbol,
        marketCap = marketCap,
        price = price,
        priceChange1d = priceChange1d,
        priceChange1h = priceChange1h,
        priceChange1w = priceChange1w,
        rank = rank,
        websiteUrl = websiteUrl,
        twitterUrl = twitterUrl
    )
}