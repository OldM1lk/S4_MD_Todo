package com.example.todo.feature_todo.presentation.add_edit_todo

sealed class AddEditTodoEvent {
    data class OnTitleChange(val title: String) : AddEditTodoEvent()
    data class OnDescriptionChange(val description: String) : AddEditTodoEvent()
    data class OnDeadlineDateChange(val date: String) : AddEditTodoEvent()
    data class OnDeadlineTimeChange(val time: String) : AddEditTodoEvent()
    data class OnPriorityChange(val priority: String) : AddEditTodoEvent()
    object OnSaveTodoClick : AddEditTodoEvent()
    object ShowPriorityDropDownMenu : AddEditTodoEvent()
    object HidePriorityDropDownMenu : AddEditTodoEvent()
    data class OnPriorityDropDownMenuExpandedChange(val expanded: Boolean) : AddEditTodoEvent()
    data class OnCategoryDropDownMenuExpandedChange(val expanded: Boolean) : AddEditTodoEvent()
    object HideCategoryDropDownMenu : AddEditTodoEvent()
    data class OnCategoryChange(val category: String) : AddEditTodoEvent()
}