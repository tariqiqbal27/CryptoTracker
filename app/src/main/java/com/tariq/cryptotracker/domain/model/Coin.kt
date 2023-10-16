package com.tariq.cryptotracker.domain.model

data class Coin(
    val id: String,
    val name: String,
    val icon: String,
    val price: Double,
    val priceChanged: Double,
    val rank: Int,
    val symbol:String
)
