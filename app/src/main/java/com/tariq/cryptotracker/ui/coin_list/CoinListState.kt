package com.tariq.cryptotracker.ui.coin_list

import com.tariq.cryptotracker.domain.model.Coin

data class CoinListState(
    val isLoading:Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
