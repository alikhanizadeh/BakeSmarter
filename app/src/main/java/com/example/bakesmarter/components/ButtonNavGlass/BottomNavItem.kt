package com.example.bakesmarter.components.ButtonNavGlass


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavItem(
    val title: String,
    val icon: ImageVector
) {
    Contacts("Contacts", Icons.Outlined.Person),
    Chats("Chats", Icons.Outlined.Build),
    Settings("Settings", Icons.Outlined.Settings)
}

