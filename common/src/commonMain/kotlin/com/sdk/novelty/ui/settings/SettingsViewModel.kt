package com.sdk.novelty.ui.settings

import androidx.compose.runtime.*
import com.sdk.novelty.data.CachingManager
import com.sdk.novelty.data.appDispatcher
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val cachingManager: CachingManager
): ViewModel() {
    var index by mutableStateOf(0)
        private set

    init {
        observerValue()
    }

    private fun observerValue() {
        viewModelScope.launch {
            cachingManager.getThemeIndex().collectLatest {
                index = it
            }
        }
    }

    fun saveThemeIndex(index: Int) {
        viewModelScope.launch(appDispatcher) {
            cachingManager.saveThemeIndex(index)
            observerValue()
        }
    }
}