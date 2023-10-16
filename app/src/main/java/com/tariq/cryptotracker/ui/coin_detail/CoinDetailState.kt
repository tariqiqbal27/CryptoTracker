package com.tariq.cryptotracker.ui.coin_detail

import com.tariq.cryptotracker.domain.model.CoinDetail

data class CoinDetailState (
    val isLoading: Boolean = false,
    val coin:CoinDetail? = null,
    val error:String = ""

)