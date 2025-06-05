package com.example.todo.feature_todo.presentation.util

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable object Todos: Screen()
    @Serializable data class AddEditTodo(val id: Int?): Screen()
}