package com.sdk.novelty.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsDTO(
    val articles: List<Article> = emptyList(),
    val status: String,
    val totalResults: Int
)