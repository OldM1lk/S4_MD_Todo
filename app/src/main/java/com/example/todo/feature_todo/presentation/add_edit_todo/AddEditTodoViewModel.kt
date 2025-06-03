package com.example.todo.feature_todo.presentation.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.feature_todo.domain.model.Todo
import com.example.todo.feature_todo.domain.use_case.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases
) : ViewModel() {

    var todo by mutableStateOf<Todo?>(null)
        private set
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set
    var deadline by mutableLongStateOf(0)
        private set
    var priority by mutableIntStateOf(0)
        private set

    fun onEvent(event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.OnDeadlineChange -> {
                deadline = event.deadline
            }

            is AddEditTodoEvent.OnDescriptionChange -> {
                description = event.description
            }

            is AddEditTodoEvent.OnPriorityChange -> {
                priority = event.priority
            }

            AddEditTodoEvent.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if (title.isNotBlank() && deadline.toInt() != 0) {
                        todoUseCases.addTodo(
                            Todo(
                                id = todo?.id,
                                title = title,
                                description = description,
                                deadline = deadline,
                                priority = priority,
                                isDone = todo?.isDone == false
                            )
                        )
                    }
                }
            }

            is AddEditTodoEvent.OnTitleChange -> {
                title = event.title
            }
        }
    }

    fun getTodoById(id: Int) {
        viewModelScope.launch {
            todo = todoUseCases.getTodo(id)
        }
    }
}