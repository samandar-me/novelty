package com.sdk.novelty.ui.saved

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

internal object SavedTab: Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = Icons.Default.Bookmark)
            return remember { TabOptions(
                index = 1u,
                title = "Saved",
                icon = icon
            ) }
        }
    @Composable
    override fun Content() {
        Text(text = "Saved")
    }
}