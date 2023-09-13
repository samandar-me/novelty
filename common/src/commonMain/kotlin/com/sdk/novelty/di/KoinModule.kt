package com.sdk.novelty.di

import com.sdk.novelty.data.network.KtorClient
import com.sdk.novelty.data.network.KtorService
import com.sdk.novelty.data.repository.NoveltyRepositoryImpl
import com.sdk.novelty.domain.repository.NoveltyRepository
import com.sdk.novelty.domain.use_case.GetNewsUseCase
import com.sdk.novelty.ui.headlines.HomeViewModel
import com.sdk.novelty.ui.search.SearchViewModel
import com.sdk.novelty.util.viewModelDefinition
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.BODY
            }
        }
    }
    factory<KtorService> {
        KtorClient(get())
    }
    singleOf(::NoveltyRepositoryImpl) {
        bind<NoveltyRepository>()
    }
    factory { GetNewsUseCase() }
    factory { HomeViewModel(get()) }
    factory { SearchViewModel(get()) }
}