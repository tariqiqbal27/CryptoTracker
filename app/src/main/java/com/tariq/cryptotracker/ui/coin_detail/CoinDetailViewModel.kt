package com.tariq.cryptotracker.ui.coin_detail

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tariq.cryptotracker.data.remote.dto.toCoinDetail
import com.tariq.cryptotracker.domain.model.CoinDetail
import com.tariq.cryptotracker.domain.repository.CoinRepository
import com.tariq.cryptotracker.util.Constants
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
class CoinDetailViewModel @Inject constructor(
    private val repository: CoinRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoinDetail(coinId)
        }
    }


    private fun getCoinDetail(coinId: String) {
        getCoinDetailFromApi(coinId).onEach { res ->
            when (res) {
                is Resource.Success -> _state.value = CoinDetailState(coin = res.data)
                is Resource.Error -> _state.value =
                    CoinDetailState(error = res.message ?: "An unexpected error occurred")

                is Resource.Loading -> _state.value = CoinDetailState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }


    private fun getCoinDetailFromApi(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetail = repository.getCoinDetail(coinId).toCoinDetail()
            emit(Resource.Success(coinDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message() ?: "an error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Server not found"))
        }
    }

}