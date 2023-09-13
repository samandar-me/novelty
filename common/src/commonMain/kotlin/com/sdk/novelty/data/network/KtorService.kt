package com.sdk.novelty.data.network

import com.sdk.novelty.data.model.NewsDTO

interface KtorService {
    suspend fun getNews(query: String): NewsDTO
}