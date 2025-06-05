package com.example.todo.feature_todo.presentation.todos

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Category
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Tasks : BottomNavItem("tasks", Icons.Filled.List, "Задачи")
    object Categories : BottomNavItem("categories", Icons.Filled.Category, "Категории")
}