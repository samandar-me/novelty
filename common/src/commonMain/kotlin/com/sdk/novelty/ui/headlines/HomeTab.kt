package com.sdk.novelty.ui.headlines

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.sdk.novelty.util.Other
import org.koin.compose.rememberKoinInject

internal object HomeTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = Icons.Default.Home)
            return remember {
                TabOptions(
                    index = 0u,
                    title = "Home",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val vm = rememberKoinInject<HomeViewModel>()
        val state = vm.state.collectAsState()
        Navigator(
            HomeScreen(
                state = state,
                loadNews = vm::loadNews
            ),
            onBackPressed = {
                Other.isBottomBarVisible = true
                true
            }
        )
    }
}