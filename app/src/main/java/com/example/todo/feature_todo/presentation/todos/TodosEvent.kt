package com.example.todo.feature_todo.presentation.todos

import com.example.todo.feature_todo.domain.model.Todo

sealed class TodosEvent {
    data class OnDoneChange(val todo: Todo, val isDone: Boolean) : TodosEvent()
    data class DeleteTodo(val todo: Todo) : TodosEvent()
    object OnUndoDeleteClick : TodosEvent()
    data class FilterByCategory(val category: String?) : TodosEvent()
}