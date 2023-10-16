package com.tariq.cryptotracker.data.remote

import com.tariq.cryptotracker.data.remote.dto.CoinDetailDto
import com.tariq.cryptotracker.data.remote.dto.CoinsDto
import com.tariq.cryptotracker.domain.model.CoinDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinStatsApi {

    @GET("/coins?limit=15")
    suspend fun getCoins() : CoinsDto

    @GET("/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId")coinId: String) : CoinDetailDto

}