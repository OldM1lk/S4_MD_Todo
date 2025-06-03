package com.example.todo.feature_todo.presentation.add_edit_todo

sealed class AddEditTodoEvent {
    data class OnTitleChange(val title: String) : AddEditTodoEvent()
    data class OnDescriptionChange(val description: String) : AddEditTodoEvent()
    data class OnDeadlineChange(val deadline: Long) : AddEditTodoEvent()
    data class OnPriorityChange(val priority: Int) : AddEditTodoEvent()
    object OnSaveTodoClick : AddEditTodoEvent()
}