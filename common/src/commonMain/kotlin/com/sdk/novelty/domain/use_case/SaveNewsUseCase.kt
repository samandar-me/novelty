package com.sdk.novelty.domain.use_case

import com.sdk.novelty.domain.model.NewsEntity
import com.sdk.novelty.domain.repository.LocalRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

typealias SaveNewsBaseUseCase = BaseUseCase<NewsEntity,Unit>

class SaveNewsUseCase : KoinComponent, SaveNewsBaseUseCase {
    private val localRepository: LocalRepository by inject()
    override suspend fun invoke(parameter: NewsEntity) {
        localRepository.saveNews(parameter)
    }
}