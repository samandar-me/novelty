package com.sdk.novelty.domain.use_case

import com.sdk.novelty.domain.model.News
import com.sdk.novelty.domain.repository.NoveltyRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

typealias GetNewsBaseUseCase = BaseUseCase<String,Flow<List<News>>>

class GetNewsUseCase : KoinComponent, GetNewsBaseUseCase {
    private val repository: NoveltyRepository by inject()
    override suspend fun invoke(parameter: String): Flow<List<News>> {
        return repository.getTopNews(parameter)
    }
}