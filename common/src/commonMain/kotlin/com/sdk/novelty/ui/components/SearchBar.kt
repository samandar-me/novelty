package com.sdk.novelty.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester

@Composable
fun SearchBar(
    onSearch: (String) -> Unit,
    onBack: () -> Unit,
    focusRequester: FocusRequester
) {
    var query by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = query,
        placeholder = {
            Text(text = "Search...")
        },
        onValueChange = {
            query = it
            onSearch(it)
        },
        leadingIcon = {
            IconButton(
                onClick = onBack
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
            }
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    if (query.isNotBlank()) {
                        query = ""
                    } else {
                        onBack()
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "close")
            }
        }
    )
}