package com.sdk.novelty.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.sdk.novelty.ui.components.ContentList
import com.sdk.novelty.ui.components.Loading
import com.sdk.novelty.ui.components.SearchBar
import com.sdk.novelty.util.Other
import org.koin.compose.rememberKoinInject

internal object SearchScreen : Screen {
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        val vm = rememberKoinInject<SearchViewModel>()
        val state by vm.state.collectAsState()
        val nav = LocalNavigator.currentOrThrow
        val keyboardController = LocalSoftwareKeyboardController.current
        val focusRequester = remember { FocusRequester() }
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
            keyboardController?.show()
            Other.isBottomBarVisible = false
        }
        Scaffold(
            topBar = {
                SearchBar(
                    onSearch = vm::search,
                    onBack = {
                        keyboardController?.hide()
                        focusRequester.freeFocus()
                        nav.pop()
                        Other.isBottomBarVisible = true
                    },
                    focusRequester = focusRequester
                )
            }
        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {
                if (state.message.isNotBlank()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = state.message, fontSize = 25.sp)
                    }
                }
                if (state.isLoading) {
                    Loading()
                }
                ContentList(
                    list = state.success
                )
            }
        }
    }
}