package com.sdk.novelty.domain.use_case

import com.sdk.novelty.domain.model.NewsEntity
import com.sdk.novelty.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

typealias GetNewsByIdBaseUseCase = BaseUseCase<String,Flow<NewsEntity?>>

class GetNewsByIdUseCase : KoinComponent, GetNewsByIdBaseUseCase {
    private val localRepository by inject<LocalRepository>()
    override suspend fun invoke(parameter: String): Flow<NewsEntity?> {
        return localRepository.getById(parameter)
    }
}