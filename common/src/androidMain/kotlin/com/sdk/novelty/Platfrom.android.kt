package com.sdk.novelty

class AndroidPlatform: Platform {
    override val screenType: ScreenSizeType
        get() = ScreenSizeType.SMALL
}

actual fun getScreenSize(): Platform = AndroidPlatform()