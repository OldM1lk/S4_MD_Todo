package com.example.todo.feature_todo.presentation.util

import com.example.todo.feature_todo.domain.model.Todo
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    data class Todos(val deletedTodo: Todo?): Screen()
    data class AddEditTodo(val id: Int?): Screen()
}