package com.sdk.novelty.domain.use_case

import com.sdk.novelty.domain.model.NewsEntity
import com.sdk.novelty.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

typealias GetLocalNewsBaseUseCase = BaseUseCase<Unit,Flow<List<NewsEntity>>>

class GetLocalNewsUseCase : KoinComponent, GetLocalNewsBaseUseCase {
    private val repo by inject<LocalRepository>()

    override suspend fun invoke(parameter: Unit): Flow<List<NewsEntity>> {
        return repo.getAllNews()
    }
}