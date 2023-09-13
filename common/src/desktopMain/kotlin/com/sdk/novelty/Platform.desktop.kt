package com.sdk.novelty

class DesktopPlatform: Platform {
    override val screenType: ScreenSizeType
        get() = ScreenSizeType.LARGE
}

actual fun getScreenSize(): Platform = DesktopPlatform()