package com.sdk.novelty

interface Platform {
    val screenType: ScreenSizeType
}

expect fun getScreenSize(): Platform

enum class ScreenSizeType {
    SMALL,
    LARGE
}