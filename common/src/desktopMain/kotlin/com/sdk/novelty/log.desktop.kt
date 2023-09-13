package com.sdk.novelty

import com.sdk.novelty.util.TAG
import org.jetbrains.skia.skottie.Logger
import java.io.Console

actual fun printLog(msg: String) {
    println("$TAG, $msg")
}