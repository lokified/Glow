package com.loki.glow.data.remote.dto

data class Supply(
    val circulating: String,
    val confirmed: Boolean,
    val max: String,
    val sypplyAt: Int,
    val total: String
)