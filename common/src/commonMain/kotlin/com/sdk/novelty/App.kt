package com.sdk.novelty

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.sdk.novelty.ui.components.AnimVisible
import com.sdk.novelty.ui.components.RailItem
import com.sdk.novelty.ui.components.TabItem
import com.sdk.novelty.ui.headlines.HomeTab
import com.sdk.novelty.ui.saved.SavedTab
import com.sdk.novelty.ui.settings.Settings
import com.sdk.novelty.ui.settings.SettingsViewModel
import com.sdk.novelty.util.Other
import org.koin.compose.rememberKoinInject


@Composable
fun App(
    isDark: Boolean
) {
    val vm = rememberKoinInject<SettingsViewModel>()
    val theme = when(vm.index) {
        0 -> isDark
        1 -> false
        else -> true
    }
    NoveltyTheme(
        isDark = theme
    ) {
        BoxWithConstraints {
            TabNavigator(HomeTab) {
                if (maxWidth.value < 500f) {
                    Scaffold(
                        bottomBar = {
                            AnimVisible(
                                isVisible = Other.isBottomBarVisible
                            ) {
                                NavigationBar {
                                    TabItem(HomeTab)
                                    TabItem(SavedTab)
                                    TabItem(Settings(vm))
                                }
                            }
                        }
                    ) {
                        CurrentTab()
                    }
                } else {
                    Box(modifier = Modifier.padding(start = 80.dp)) {
                        CurrentTab()
                    }
                    AnimVisible(
                        isVisible = Other.isBottomBarVisible
                    ) {
                        NavigationRail {
                            RailItem(HomeTab)
                            RailItem(SavedTab)
                            //RailItem(Settings)
                        }
                    }
                }
            }
        }
    }
}