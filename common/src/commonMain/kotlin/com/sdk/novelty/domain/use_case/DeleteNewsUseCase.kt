package com.sdk.novelty.domain.use_case

import com.sdk.novelty.domain.repository.LocalRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

typealias DeleteNewsBaseUseCase = BaseUseCase<Long,Unit>

class DeleteNewsUseCase : KoinComponent, DeleteNewsBaseUseCase {
    private val localRepository by inject<LocalRepository>()

    override suspend fun invoke(parameter: Long) {
        localRepository.deleteById(parameter)
    }
}