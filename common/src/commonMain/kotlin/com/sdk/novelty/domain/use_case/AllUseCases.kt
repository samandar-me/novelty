package com.sdk.novelty.domain.use_case

data class AllUseCases(
    val getNewsUseCase: GetNewsUseCase,
    val deleteNewsUseCase: DeleteNewsUseCase,
    val getLocalNewsUseCase: GetLocalNewsUseCase,
    val saveNewsUseCase: SaveNewsUseCase,
    val getNewsByIdUseCase: GetNewsByIdUseCase
)