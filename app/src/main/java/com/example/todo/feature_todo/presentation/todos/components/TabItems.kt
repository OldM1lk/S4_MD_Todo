package com.example.todo.feature_todo.presentation.todos.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Folder
import com.example.todo.feature_todo.presentation.util.Screen

val tabItems = listOf(
    TabItem(
        title = "Tasks",
        unselectedIcon = Icons.Outlined.Checklist,
        selectedIcon = Icons.Filled.Checklist,
        screen = Screen.Todos
    ),
    TabItem(
        title = "Categories",
        unselectedIcon = Icons.Outlined.Folder,
        selectedIcon = Icons.Filled.Folder,
        screen = Screen.Categories
    )
)