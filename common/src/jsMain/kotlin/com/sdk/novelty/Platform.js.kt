package com.sdk.novelty

class JsPlatform: Platform {
    override val screenType: ScreenSizeType
        get() = ScreenSizeType.LARGE
}

actual fun getScreenSize(): Platform = JsPlatform()