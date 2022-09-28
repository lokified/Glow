package com.loki.glow.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.glow.domain.use_cases.CoinUseCase
import com.loki.glow.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinUseCase: CoinUseCase
): ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        coinUseCase.getCoinList().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = HomeState(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    _state.value = HomeState(
                        coins = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = HomeState(
                        error = result.message ?: "unexpected error"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}