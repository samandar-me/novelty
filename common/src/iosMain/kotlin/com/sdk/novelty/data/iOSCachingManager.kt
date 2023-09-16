package com.sdk.novelty.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import platform.Foundation.NSNumber
import platform.Foundation.NSUserDefaults
import platform.Foundation.didChangeValueForKey

actual class CachingManager {

    private val userDefault = NSUserDefaults.standardUserDefaults
    actual suspend fun saveThemeIndex(index: Int) {
        val nsNumber = NSNumber(index)
        userDefault.setInteger(index.toLong(), "theme_index")
    }

    actual fun getThemeIndex(): Flow<Int> = flow {
        val s = userDefault.integerForKey("theme_index")
        val nsNumber = NSNumber(s.toInt())
        emit(s.toInt())
    }
}