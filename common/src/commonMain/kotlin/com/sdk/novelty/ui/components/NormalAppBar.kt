package com.sdk.novelty.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NormalAppBar(
    onOpen: () -> Unit,
    title: String
) {
    CenterAlignedTopAppBar(
        title = {
                Text(text = title)
        },
        actions = {
            IconButton(
                onClick = onOpen
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            }
        }
    )
}