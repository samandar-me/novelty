package com.sdk.novelty.ui.headlines

import com.sdk.novelty.domain.model.News
import com.sdk.novelty.domain.use_case.AllUseCases
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.supervisorScope

class HomeViewModel(
    private val allUseCases: AllUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state get() = _state.asStateFlow()

    private val generalList = mutableListOf<News>()
    private val businessList = mutableListOf<News>()
    private val techList = mutableListOf<News>()

    init {
        println("@@@init")
        loadNews(0)
    }

    fun loadNews(index: Int) {
        when (index) {
            0 -> {
                if (generalList.isEmpty()) {
                    fetchNews(generalList, "general")
                }
            }

            1 -> {
                if (businessList.isEmpty()) {
                    fetchNews(businessList, "business")
                }
            }

            else -> {
                if (techList.isEmpty()) {
                    fetchNews(techList, "technology")
                }
            }
        }
    }

    private fun fetchNews(newsList: MutableList<News>, query: String) {
        viewModelScope.launch {
            allUseCases.getNewsUseCase(query)
                .onStart {
                    _state.update {
                        it.copy(isLoading = true, isLoaded = false)
                    }
                }
                .catch { t ->
                    println("@@@${t.message}")
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = "Error has occurred",
                            isLoaded = false
                        )
                    }
                }.collectLatest { list ->

                    newsList.addAll(list)

                    _state.update {
                        it.copy(
                            isLoading = false,
                            success = listOf(generalList, businessList, techList),
                            isLoaded = true
                        )
                    }
                }
        }
    }
}