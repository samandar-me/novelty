package com.sdk.novelty

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sdk.novelty.ui.theme.DarkColors
import com.sdk.novelty.ui.theme.LightColors
import platform.CoreGraphics.CGFloat
import platform.Foundation.valueForKey
import platform.UIKit.UIApplication
import platform.UIKit.UIColor
import platform.UIKit.UINavigationBarAppearance
import platform.UIKit.UIScreen
import platform.UIKit.UIStatusBarStyle
import platform.UIKit.UIView

@Composable
actual fun NoveltyTheme(
    isDark: Boolean,
    content: @Composable () -> Unit
) {
    val color = if(isDark) DarkColors else LightColors
    MaterialTheme(
        colorScheme = color,
        content = content
    )
}
fun Color.toUIColor(): UIColor {
    val red = (this.red * 255).toDouble()
    val green = (this.green * 255).toDouble()
    val blue = (this.blue * 255).toDouble()
    val alpha = (this.alpha * 255).toDouble()

    return UIColor(red = red, green = green, blue = blue, alpha = alpha)
}