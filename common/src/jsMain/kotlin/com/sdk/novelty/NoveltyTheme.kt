package com.sdk.novelty

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.sdk.novelty.ui.theme.DarkColors
import com.sdk.novelty.ui.theme.LightColors

@Composable
actual fun NoveltyTheme(isDark: Boolean, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (isDark) DarkColors else LightColors,
        content = content
    )
}