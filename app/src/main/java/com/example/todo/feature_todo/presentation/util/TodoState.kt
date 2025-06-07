package com.example.todo.feature_todo.presentation.util

import com.example.todo.feature_todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class TodoState(
    val currentTodoId: Int? = null,
    val currentTodoTitle: String = "",
    val currentTodoDeadlineDate: String = DateTimeFormatter
        .ofPattern("dd MMM yyyy")
        .format(LocalDate.now()),
    val currentTodoDeadlineTime: String = DateTimeFormatter
        .ofPattern("HH:mm")
        .format(LocalTime.NOON),
    val currentTodoPriority: String = Todo.todoPriorities[2],
    val currentTodoCategory: String? = null,
    val currentTodoDescription: String = "",
    val isCurrentTodoDone: Boolean = false,
    val todos: Flow<List<Todo>> = emptyFlow(),
    val recentlyDeletedTodo: Todo? = null,
    val isPriorityDropDownMenuExpanded: Boolean = false,
    val isCategoryDropDownMenuExpanded: Boolean = false
)