package com.sdk.novelty.domain.repository

import com.sdk.novelty.domain.model.NewsEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun saveNews(newsEntity: NewsEntity)
    fun getAllNews(): Flow<List<NewsEntity>>

    suspend fun deleteById(id: Long)
    suspend fun getById(sourceName: String): Flow<NewsEntity?>
}