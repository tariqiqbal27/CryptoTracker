package com.tariq.cryptotracker.data.remote.dto


import com.google.gson.annotations.SerializedName

data class CoinsDto(
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("result")
    val result: List<Result>
)