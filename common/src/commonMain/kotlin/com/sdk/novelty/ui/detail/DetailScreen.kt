package com.sdk.novelty.ui.detail

import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.sdk.novelty.util.Other

internal data class DetailScreen (
    private val id: Int
): Screen {

    init {
        Other.isBottomBarVisible = false
    }

    @Composable
    override fun Content() {
        val nav = LocalNavigator.currentOrThrow
        Scaffold {
            Button(
                onClick = {
                    nav.pop()
                    Other.isBottomBarVisible = true
                }
            ) {
                Text(text = "Back")
            }
        }
    }
}