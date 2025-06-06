package com.example.todo.feature_todo.presentation.todos.components

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.todo.feature_todo.presentation.util.Screen

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val screen: Screen
)