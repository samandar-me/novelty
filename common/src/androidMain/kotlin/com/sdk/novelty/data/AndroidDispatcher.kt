package com.sdk.novelty.data

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

actual val appDispatcher: CoroutineContext = Dispatchers.IO