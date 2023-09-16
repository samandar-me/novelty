package com.sdk.novelty.data.network

import com.sdk.novelty.data.model.NewsDTO
import com.sdk.novelty.util.API_KEY
import com.sdk.novelty.util.API_KEY2
import com.sdk.novelty.util.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.parameters

class KtorClient(
    private val client: HttpClient
) : KtorService {
    override suspend fun getNews(query: String): NewsDTO {
        val r = client.get(BASE_URL) {
            parameters {
                parameter("q", query)
                parameter("apiKey", API_KEY2)
            }
        }
        return r.body<NewsDTO>()
    }

    override fun close() {
        client.close()
    }
}