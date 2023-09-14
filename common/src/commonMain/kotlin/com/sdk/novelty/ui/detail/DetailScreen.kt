package com.sdk.novelty.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.sdk.novelty.domain.model.News
import com.sdk.novelty.util.Other
import com.sdk.novelty.util.byteArrayToImageBitmap
import dev.icerock.moko.mvvm.compose.getViewModel
import org.koin.compose.rememberKoinInject

internal data class DetailScreen (
    private val news: News,

): Screen {

    init {
        Other.isBottomBarVisible = false
    }

    @Composable
    override fun Content() {
        val nav = LocalNavigator.currentOrThrow
        val vm = rememberKoinInject<DetailViewModel>()
        Scaffold {

        }
    }
}