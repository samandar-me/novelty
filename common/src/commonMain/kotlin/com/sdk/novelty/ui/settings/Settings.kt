package com.sdk.novelty.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.sdk.novelty.ui.components.ThemeDialog

internal data class Settings(
    private val vm: SettingsViewModel
) : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = Icons.Default.Settings)
            return remember {
                TabOptions(
                    index = 2u,
                    title = "Settings",
                    icon = icon
                )
            }
        }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        var isDialogOpen by remember { mutableStateOf(false) }
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Settings")
                    }
                )
            }
        ) {
            ListItem(
                modifier = Modifier
                    .padding(it)
                    .clickable {
                        isDialogOpen = true
                    },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Palette,
                        contentDescription = "theme"
                    )
                },
                headlineContent = {
                    Text(text = "Theme")
                },
            )
            if (isDialogOpen) {
                ThemeDialog(
                    currentTheme = vm.index,
                    onClose = { isDialogOpen = false },
                    onChecked = vm::saveThemeIndex
                )
            }
        }
    }
}