package com.sdk.novelty.ui.headlines.top

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TopBarItem(
    val icon: ImageVector,
    val label: String
)  {
    object General: TopBarItem(
        icon = Icons.Default.Language,
        label = "General"
    )
    object Business: TopBarItem(
        icon = Icons.Default.Business,
        label = "Business"
    )
    object Traveling: TopBarItem(
        icon = Icons.Default.Computer,
        label = "Technology"
    )
}