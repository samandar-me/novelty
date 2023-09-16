package com.sdk.novelty.di

import com.sdk.novelty.data.network.ImageLoader
import com.sdk.novelty.data.network.ImageLoaderImpl
import com.sdk.novelty.data.network.KtorClient
import com.sdk.novelty.data.network.KtorService
import com.sdk.novelty.data.repository.LocalRepositoryImpl
import com.sdk.novelty.data.repository.NoveltyRepositoryImpl
import com.sdk.novelty.database.NoveltyDatabase
import com.sdk.novelty.domain.repository.LocalRepository
import com.sdk.novelty.domain.repository.NoveltyRepository
import com.sdk.novelty.domain.use_case.AllUseCases
import com.sdk.novelty.domain.use_case.DeleteNewsUseCase
import com.sdk.novelty.domain.use_case.GetLocalNewsUseCase
import com.sdk.novelty.domain.use_case.GetNewsByIdUseCase
import com.sdk.novelty.domain.use_case.GetNewsUseCase
import com.sdk.novelty.domain.use_case.SaveNewsUseCase
import com.sdk.novelty.ui.detail.DetailViewModel
import com.sdk.novelty.ui.headlines.HomeViewModel
import com.sdk.novelty.ui.saved.SavedViewModel
import com.sdk.novelty.ui.search.SearchViewModel
import com.sdk.novelty.ui.settings.SettingsViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
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
        }
    }
    factory<KtorService> {
        KtorClient(get())
    }
    singleOf(::NoveltyRepositoryImpl) {
        bind<NoveltyRepository>()
    }

    singleOf(::ImageLoaderImpl) {
        bind<ImageLoader>()
    }

    singleOf(::LocalRepositoryImpl) {
        bind<LocalRepository>()
    }

    factory { AllUseCases(
        getLocalNewsUseCase = GetLocalNewsUseCase(),
        deleteNewsUseCase = DeleteNewsUseCase(),
        saveNewsUseCase = SaveNewsUseCase(),
        getNewsByIdUseCase = GetNewsByIdUseCase(),
        getNewsUseCase = GetNewsUseCase()
    ) }
    factory { HomeViewModel(get()) }
    factory { SearchViewModel(get()) }
    factory { DetailViewModel(get(), get(), get()) }
    factory { SavedViewModel(get()) }
    factory { SettingsViewModel(get()) }
}