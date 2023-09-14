package com.sdk.novelty.domain.model

data class NewsEntity(
    val id: Long = 0,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val sourceName: String,
    val sourceId: String,
    val title: String,
    val url: String,
    val imageBytes: ByteArray
)