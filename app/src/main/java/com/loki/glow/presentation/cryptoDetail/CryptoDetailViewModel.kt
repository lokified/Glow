package com.loki.glow.presentation.cryptoDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.glow.domain.use_cases.CoinUseCase
import com.loki.glow.util.Constants
import com.loki.glow.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val coinUseCase: CoinUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(CoinState())
    val state: State<CoinState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        coinUseCase.getCoin(coinId).onEach { result ->

            when(result) {
                is Resource.Loading -> {
                    _state.value = CoinState(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    _state.value = CoinState(
                        coin = result.data
                    )
                }
                is Resource.Error -> {
                    _state.value = CoinState(
                        error = result.message ?: "unexpected error"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}