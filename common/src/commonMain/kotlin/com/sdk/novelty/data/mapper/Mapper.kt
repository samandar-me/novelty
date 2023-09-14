package com.sdk.novelty.data.mapper

import com.sdk.novelty.data.model.Article
import com.sdk.novelty.data.model.NewsDTO
import com.sdk.novelty.domain.model.News
import com.sdk.novelty.domain.model.NewsEntity
import database.NoveltyEntity

fun Article.toNews(): News {
    return News(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        sourceName = source?.name,
        sourceId = source?.id,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}
fun NoveltyEntity.toNewsEntity(): NewsEntity {
    return NewsEntity(
        id = id,
        author = author,
        content = content,
        description = desc,
        publishedAt = date,
        sourceName = sourceName,
        sourceId = sourceId,
        title = title,
        url = url,
        imageBytes = imageBytes
    )
}