package com.sdk.novelty.ui.headlines

import com.sdk.novelty.domain.model.News

data class HomeState(
    val success: List<List<News>> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoaded: Boolean = false
)