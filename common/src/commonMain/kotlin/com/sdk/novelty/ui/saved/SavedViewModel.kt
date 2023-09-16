package com.sdk.novelty.ui.saved

import androidx.compose.runtime.*
import com.sdk.novelty.data.appDispatcher
import com.sdk.novelty.domain.model.News
import com.sdk.novelty.domain.model.NewsEntity
import com.sdk.novelty.domain.use_case.AllUseCases
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SavedViewModel(
    private val allUseCases: AllUseCases
) : ViewModel() {
    var localNewsList = mutableStateListOf<NewsEntity>()
        private set
    private val cachedList = mutableStateListOf<NewsEntity>()

    init {
        viewModelScope.launch {
            allUseCases.getLocalNewsUseCase(Unit)
                .collectLatest {
                    localNewsList.addAll(it)
                    cachedList.addAll(it)
                }
        }
    }
    fun searchNews(query: String) {
        if(query.isNotBlank()) {
            val filteredNews = cachedList.filter {
                it.title.trim().lowercase().contains(query.trim().lowercase()) || it.sourceName.trim().lowercase().contains(query.trim().lowercase())
            }
            localNewsList.clear()
            localNewsList.addAll(filteredNews)
        } else {
            reloadList()
        }
    }
    fun reloadList() {
        viewModelScope.launch(appDispatcher) {
            localNewsList.clear()
            localNewsList.addAll(cachedList)
        }
    }
}