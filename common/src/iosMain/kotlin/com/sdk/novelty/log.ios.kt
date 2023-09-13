package com.sdk.novelty

import com.sdk.novelty.util.TAG
import platform.Foundation.NSLog

actual fun printLog(msg: String) {
    NSLog(TAG, msg)
}