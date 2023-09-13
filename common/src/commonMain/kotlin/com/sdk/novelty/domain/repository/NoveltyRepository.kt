package com.sdk.novelty.domain.repository

import com.sdk.novelty.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NoveltyRepository {
    suspend fun getTopNews(query: String): Flow<List<News>>
}