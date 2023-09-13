package com.sdk.novelty.data.mapper

import com.sdk.novelty.data.model.Article
import com.sdk.novelty.data.model.NewsDTO
import com.sdk.novelty.domain.model.News

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