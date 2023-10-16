package com.tariq.cryptotracker.domain.model

data class CoinDetail(
    val id: String,
    val icon: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val price: Double,
    val marketCap: Double,
    val priceChange1h: Double,
    val priceChange1d: Double,
    val priceChange1w: Double,
    val websiteUrl: String,
    val twitterUrl: String
)
