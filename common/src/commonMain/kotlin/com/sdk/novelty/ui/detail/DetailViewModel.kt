package com.sdk.novelty.ui.detail

import androidx.compose.runtime.mutableStateOf
import com.sdk.novelty.data.network.ImageLoader
import com.sdk.novelty.domain.use_case.AllUseCases
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailViewModel(
    private val imageLoader: ImageLoader,
    private val allUseCases: AllUseCases
) : ViewModel() {

    var isLoaded = false
    var image = mutableStateOf(ByteArray(0))

    init {
        viewModelScope.launch {
            image.value = imageLoader.loadImage("https://247wallst.com/wp-content/uploads/2022/10/imageForEntry29-eCQ.jpg")
            isLoaded = true

            allUseCases.getLocalNewsUseCase(Unit)
                .collectLatest {
                    println("@@@$it")
                }
        }
    }
}