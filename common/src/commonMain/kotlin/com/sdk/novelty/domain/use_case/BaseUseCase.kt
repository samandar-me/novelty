package com.sdk.novelty.domain.use_case

interface BaseUseCase <in Parameter, out Result> {
    suspend operator fun invoke(parameter: Parameter): Result
}