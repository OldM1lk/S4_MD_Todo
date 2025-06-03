package com.example.todo.feature_todo.presentation.util

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    object Todos: Screen()
    data class AddEditTodo(val id: Int?): Screen()
}