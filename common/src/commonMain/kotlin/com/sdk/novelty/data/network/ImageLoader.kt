package com.sdk.novelty.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface ImageLoader {
    suspend fun loadImage(link: String): ByteArray
}

class ImageLoaderImpl(
    private val httpClient: HttpClient
): ImageLoader {
    override suspend fun loadImage(link: String): ByteArray {
        return try {
            val byteArray = httpClient.get(link).body<ByteArray>()
            httpClient.close()
            return byteArray
        } catch (e: Exception) {
            ByteArray(0)
        }
    }
}