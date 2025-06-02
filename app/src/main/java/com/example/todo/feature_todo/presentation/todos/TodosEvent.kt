package com.example.todo.feature_todo.presentation.todos

import com.example.todo.feature_todo.domain.model.Todo

sealed class TodosEvent {
    data class OnDoneChange(val todo: Todo, val isDone: Boolean): TodosEvent()
    object OnUndoDeleteClick: TodosEvent()
}