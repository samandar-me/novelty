package com.sdk.novelty

import android.util.Log
import com.sdk.novelty.util.TAG

actual fun printLog(msg: String) {
    Log.d(TAG, "printLog: $msg")
}