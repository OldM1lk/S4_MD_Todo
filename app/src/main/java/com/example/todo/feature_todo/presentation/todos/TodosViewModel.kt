package com.example.todo.feature_todo.presentation.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.feature_todo.domain.model.Todo
import com.example.todo.feature_todo.domain.use_case.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases
) : ViewModel() {

    val todos = todoUseCases.getTodos()

    private var deletedTodo: Todo? = null

    fun onEvent(event: TodosEvent) {
        when (event) {
            is TodosEvent.OnDoneChange -> {
                viewModelScope.launch {
                    todoUseCases.addTodo(
                        event.todo.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }

            TodosEvent.OnUndoDeleteClick -> {
                deletedTodo?.let {
                    viewModelScope.launch {
                        todoUseCases.addTodo(it)
                    }
                }
            }
        }
    }
}