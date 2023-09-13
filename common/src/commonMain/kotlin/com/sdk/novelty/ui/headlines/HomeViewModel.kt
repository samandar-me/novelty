package com.sdk.novelty.ui.headlines

import com.sdk.novelty.domain.use_case.GetNewsUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state get() = _state.asStateFlow()

    init {
        loadNews(0)
    }

    fun loadNews(index: Int) {

        val query = when(index) {
            0 -> "general"
            1 -> "business"
            else -> "technology"
        }

        viewModelScope.launch {
            getNewsUseCase(query)
                .onStart {
                    _state.update {
                        it.copy(isLoading = true)
                    }
                }
                .catch {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = "Error has occurred"
                        )
                    }
                }.collectLatest { list ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            success = list
                        )
                    }
                }
        }
    }
}