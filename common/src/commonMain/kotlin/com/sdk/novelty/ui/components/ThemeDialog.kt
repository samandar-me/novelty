package com.sdk.novelty.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ThemeDialog(
    currentTheme: Int,
    onClose: () -> Unit,
    onChecked: (Int) -> Unit
) {
    AlertDialog(
        onDismissRequest = onClose,
        title = {
            Text(text = "Select Theme")
        },
        text = {
            Column {
                CheckText(
                    text = "System",
                    onChecked = {
                        onChecked(0)
                    },
                    isChecked = currentTheme == 0,
                )
                CheckText(
                    text = "Light",
                    onChecked = {
                        onChecked(1)
                    },
                    isChecked = currentTheme == 1,
                )
                CheckText(
                    text = "Dark",
                    onChecked = {
                        onChecked(2)
                    },
                    isChecked = currentTheme == 2,
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onClose) {
                Text(text = "OK")
            }
        }
    )
}

@Composable
fun CheckText(
    text: String,
    onChecked: () -> Unit,
    isChecked: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onChecked() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isChecked,
            onClick = {
                onChecked()
            }
        )
        Text(text = text)
    }
}