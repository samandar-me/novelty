package com.sdk.novelty

import android.app.Activity
import android.graphics.BitmapFactory
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.sdk.novelty.ui.theme.DarkColors
import com.sdk.novelty.ui.theme.LightColors

@Composable
actual fun NoveltyTheme(
    isDark: Boolean,
    content: @Composable () -> Unit
) {
    val color = if(isDark) DarkColors else LightColors

    val view = LocalView.current
    if(!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = color.primary.toArgb()
            window.navigationBarColor = color.primary.toArgb()
            WindowCompat.getInsetsController(
                window,
                view
            ).isAppearanceLightStatusBars = isDark
        }
    }
    MaterialTheme(
        colorScheme = color,
        content = content
    )
}