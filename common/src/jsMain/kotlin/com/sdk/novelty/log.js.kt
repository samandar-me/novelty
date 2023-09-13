package com.sdk.novelty

import com.sdk.novelty.util.TAG

actual fun printLog(msg: String) {
    console.log("$TAG, $msg")
}