package com.tariq.cryptotracker.ui.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tariq.cryptotracker.data.remote.dto.toCoin
import com.tariq.cryptotracker.domain.model.Coin
import com.tariq.cryptotracker.domain.repository.CoinRepository
import com.tariq.cryptotracker.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }


    private fun getCoins() {
        getDataFromRemote().onEach { result ->
            when (result) {
                is Resource.Success -> _state.value =
                    CoinListState(coins = result.data ?: emptyList())

                is Resource.Error -> _state.value =
                    CoinListState(error = result.message ?: "An unexpected error occurred")

                is Resource.Loading -> _state.value = CoinListState(isLoading = true)
                else -> {}
            }
        }.launchIn(viewModelScope)
    }


    private fun getDataFromRemote(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoin().map {
                it.toCoin()
            }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "an error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Server not found"))
        }
    }


}