package com.tariq.cryptotracker.domain.repository

import com.tariq.cryptotracker.data.remote.dto.CoinDetailDto
import com.tariq.cryptotracker.data.remote.dto.CoinsDto
import com.tariq.cryptotracker.data.remote.dto.Result
import com.tariq.cryptotracker.domain.model.CoinDetail

interface CoinRepository {
    suspend fun getCoin() : List<Result>
    suspend fun getCoinDetail(coinId: String) : CoinDetailDto
}