package com.sdk.novelty.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StackedBarChart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab

@Composable
fun RowScope.TabItem(tab: Tab) {
    val navigator = LocalTabNavigator.current
    val defaultIcon = rememberVectorPainter(image = Icons.Default.StackedBarChart)

    NavigationBarItem(
        selected = navigator.current == tab,
        onClick = { navigator.current = tab },
        icon = {
            Icon(
                painter = tab.options.icon ?: defaultIcon,
                contentDescription = tab.options.title
            )
        },
        label = {
            Text(text = tab.options.title)
        }
    )
}