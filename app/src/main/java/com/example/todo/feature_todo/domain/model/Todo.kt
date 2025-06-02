package com.example.todo.feature_todo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val description: String?,
    val deadline: Long,
    val priority: Int,
    val isDone: Boolean,
) {
    companion object {
        val todoPriorities = listOf("High", "Medium", "Low")
    }
}

class InvalidTodoException(message: String) : Exception(message)