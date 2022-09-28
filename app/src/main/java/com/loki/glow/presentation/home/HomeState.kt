package com.loki.glow.presentation.home

import com.loki.glow.domain.model.Coin


data class HomeState(
    val error: String = "",
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList()
)
