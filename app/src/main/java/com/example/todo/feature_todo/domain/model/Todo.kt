package com.example.todo.feature_todo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val description: String?,
    val deadlineDate: String,
    val deadlineTime: String,
    val priority: String,
    val isDone: Boolean,
    val category: String?
) {
    companion object {
        val todoPriorities = listOf("High", "Medium", "Low")
    }
}

class InvalidTodoException(message: String) : Exception(message)