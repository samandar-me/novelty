package com.sdk.novelty

import androidx.compose.runtime.Composable

@Composable
expect fun NoveltyTheme(
    isDark: Boolean,
    content: @Composable () -> Unit
)