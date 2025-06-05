package com.example.todo.feature_todo.presentation.todos

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.feature_todo.domain.use_case.TodoUseCases
import com.example.todo.feature_todo.presentation.util.TodoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class TodosViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases
) : ViewModel() {

    private val _todoState = MutableStateFlow<TodoState>(TodoState())
    val todoState: MutableStateFlow<TodoState> = _todoState

    init {
        _todoState.update {
            it.copy(
                todos = todoUseCases.getTodos()
            )
        }
    }

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

            is TodosEvent.DeleteTodo -> {
                viewModelScope.launch {
                    todoUseCases.deleteTodo(event.todo)
                    _todoState.update {
                        it.copy(
                            recentlyDeletedTodo = event.todo
                        )
                    }
                }
            }

            TodosEvent.OnUndoDeleteClick -> {
                _todoState.value.recentlyDeletedTodo?.let {
                    viewModelScope.launch {
                        todoUseCases.addTodo(it)
                    }
                }
            }
        }
    }
}