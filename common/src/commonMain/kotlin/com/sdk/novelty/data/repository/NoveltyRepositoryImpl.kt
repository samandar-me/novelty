package com.sdk.novelty.data.repository

import com.sdk.novelty.data.mapper.toNews
import com.sdk.novelty.data.network.KtorService
import com.sdk.novelty.domain.model.News
import com.sdk.novelty.domain.repository.NoveltyRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

class NoveltyRepositoryImpl(private val ktorService: KtorService): NoveltyRepository {
    override suspend fun getTopNews(query: String): Flow<List<News>> = flow {
        val r = ktorService.getNews(query)
        emit(r.articles.map { it.toNews() })
    }
}