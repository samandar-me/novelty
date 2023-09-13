package com.sdk.novelty.ui.search

import com.sdk.novelty.domain.model.News

data class SearchState(
    val success: List<News> = emptyList(),
    val isLoading: Boolean = false,
    val message: String = ""
)