package com.example.todo.feature_todo.presentation.util

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    object Todos : Screen()
    @Serializable
    object Categories : Screen()
    @Serializable
    data class AddEditTodo(val id: Int?) : Screen()
    @Serializable
    data class FilteredTodos(val category: String) : Screen()
}