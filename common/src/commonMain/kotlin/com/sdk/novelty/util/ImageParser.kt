package com.sdk.novelty.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

@Composable
expect fun byteArrayToImageBitmap(byteArray: ByteArray?): ImageBitmap?