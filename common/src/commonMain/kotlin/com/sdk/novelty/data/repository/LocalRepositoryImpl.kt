package com.sdk.novelty.data.repository

import com.sdk.novelty.data.mapper.toNewsEntity
import com.sdk.novelty.database.NoveltyDatabase
import com.sdk.novelty.domain.model.NewsEntity
import com.sdk.novelty.domain.repository.LocalRepository
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LocalRepositoryImpl : KoinComponent, LocalRepository {

    private val db: NoveltyDatabase by inject()

    private val queries = db.noveltyQueries
    override suspend fun saveNews(newsEntity: NewsEntity) {
        queries.saveNews(
            id = newsEntity.id,
            author = newsEntity.author,
            content = newsEntity.content,
            desc = newsEntity.description,
            date = newsEntity.publishedAt,
            sourceName = newsEntity.sourceName,
            sourceId = newsEntity.sourceId,
            title = newsEntity.title,
            url = newsEntity.url,
            imageBytes = newsEntity.imageBytes
        )
    }

    override fun getAllNews(): Flow<List<NewsEntity>> {
        return queries
            .getNews()
            .asFlow()
            .mapToList()
            .map { entities ->
                entities.map {
                    it.toNewsEntity()
                }
            }
    }

    override suspend fun deleteById(id: Long) {
        queries.deleteNews(id)
    }

    override suspend fun getById(title: String): Flow<NewsEntity?> {
        return queries
            .getNewsById(title)
            .asFlow()
            .mapToOneOrNull()
            .map { it?.toNewsEntity() }
    }
}