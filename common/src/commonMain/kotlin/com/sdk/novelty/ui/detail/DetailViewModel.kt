package com.sdk.novelty.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.sdk.novelty.data.appDispatcher
import com.sdk.novelty.data.network.ImageLoader
import com.sdk.novelty.domain.model.News
import com.sdk.novelty.domain.model.NewsEntity
import com.sdk.novelty.domain.use_case.AllUseCases
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val imageLoader: ImageLoader,
    private val allUseCases: AllUseCases,
    private val title: String
) : ViewModel() {
    var isSaved by mutableStateOf(false)
        private set
    private var id: Long = 0L
    private var imageBytes = ByteArray(0)

    init {
        loadCachedNews()
    }
    private fun loadCachedNews() {
        viewModelScope.launch {
            allUseCases.getNewsByIdUseCase(title).collectLatest { entity ->
                isSaved = entity != null
                id = entity?.id ?: 0L
            }
        }
    }
    fun saveNews(news: News) {
        viewModelScope.launch(appDispatcher) {
            if(isSaved) {
                allUseCases.deleteNewsUseCase(id)
                return@launch
            }
            if(imageBytes.isEmpty()) {
                imageBytes = imageLoader.loadImage(news.urlToImage ?: "")
            }
            allUseCases.saveNewsUseCase(
                NewsEntity(
                    id = null,
                    author = news.author ?: "",
                    content = news.content ?: "",
                    description = news.description ?: "",
                    publishedAt = news.publishedAt ?: "",
                    sourceName = news.sourceName ?: "",
                    sourceId = news.sourceId ?: "",
                    title = news.title ?: "",
                    url = news.url ?: "",
                    imageBytes = imageBytes
                )
            )
        }
    }
}