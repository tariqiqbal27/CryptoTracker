package com.tariq.cryptotracker.data.repository

import com.tariq.cryptotracker.data.remote.CoinStatsApi
import com.tariq.cryptotracker.data.remote.dto.CoinDetailDto
import com.tariq.cryptotracker.data.remote.dto.CoinsDto
import com.tariq.cryptotracker.data.remote.dto.Result
import com.tariq.cryptotracker.data.remote.dto.toCoinDetail
import com.tariq.cryptotracker.domain.model.CoinDetail
import com.tariq.cryptotracker.domain.repository.CoinRepository
import javax.inject.Inject


class CoinRepositoryImpl @Inject constructor(
    private val api: CoinStatsApi
) : CoinRepository{
    override suspend fun getCoin(): List<Result> {
        return api.getCoins().result
    }

    override suspend fun getCoinDetail(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }

}