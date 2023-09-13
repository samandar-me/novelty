package com.sdk.novelty.data.network

import com.sdk.novelty.data.model.NewsDTO
import com.sdk.novelty.util.API_KEY
import com.sdk.novelty.util.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorClient(
    private val client: HttpClient
) : KtorService {
    override suspend fun getNews(query: String): NewsDTO {
        return client.get(BASE_URL) {
            url {
                parameters.append("q",query)
                parameters.append("apiKey", API_KEY)
            }
        }.body()
    }
}