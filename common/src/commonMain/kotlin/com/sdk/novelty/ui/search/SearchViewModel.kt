package com.sdk.novelty.ui.search

import com.sdk.novelty.domain.use_case.GetNewsUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getNewsUseCase: GetNewsUseCase
): ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state get() = _state.asStateFlow()
    private var job: Job? = null

    fun search(query: String) {
        job?.cancel()
        job = viewModelScope.launch {
            delay(700L)

            getNewsUseCase(query)
                .onStart {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }.catch {
                    _state.update { it.copy(message = "Not found") }
                }
                .collectLatest { list ->
                    _state.update {
                        it.copy(success = list,isLoading = false)
                    }
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
        job = null
    }
}