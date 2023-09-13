package com.sdk.novelty

class IOSPlatform: Platform {
    override val screenType: ScreenSizeType
        get() = ScreenSizeType.SMALL
}
actual fun getScreenSize(): Platform = IOSPlatform()
