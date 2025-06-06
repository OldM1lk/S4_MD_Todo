package com.example.todo.feature_todo.presentation.add_edit_todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.feature_todo.domain.model.Todo
import com.example.todo.feature_todo.domain.use_case.TodoUseCases
import com.example.todo.feature_todo.presentation.util.TodoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases
) : ViewModel() {

    private val _todoState = MutableStateFlow(TodoState())
    val todoState: StateFlow<TodoState> = _todoState

    val categories = listOf(
        "Work",
        "Home",
        "Study",
        "Health",
        "Shopping",
        "Personal",
        "Finance"
    )

    fun onEvent(event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.OnDescriptionChange -> {
                _todoState.update {
                    it.copy(
                        currentTodoDescription = event.description
                    )
                }
            }

            is AddEditTodoEvent.OnPriorityChange -> {
                _todoState.update {
                    it.copy(
                        currentTodoPriority = event.priority
                    )
                }
            }

            AddEditTodoEvent.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if (_todoState.value.currentTodoTitle.isNotBlank()) {
                        todoUseCases.addTodo(
                            Todo(
                                id = _todoState.value.currentTodoId,
                                title = _todoState.value.currentTodoTitle,
                                description = _todoState.value.currentTodoDescription,
                                deadlineDate = _todoState.value.currentTodoDeadlineDate,
                                deadlineTime = _todoState.value.currentTodoDeadlineTime,
                                priority = _todoState.value.currentTodoPriority,
                                isDone = _todoState.value.isCurrentTodoDone,
                                category = _todoState.value.currentTodoCategory
                            )
                        )
                    }
                }
            }

            is AddEditTodoEvent.OnTitleChange -> {
                _todoState.update {
                    it.copy(
                        currentTodoTitle = event.title
                    )
                }
            }

            is AddEditTodoEvent.OnDeadlineDateChange -> {
                _todoState.update {
                    it.copy(
                        currentTodoDeadlineDate = event.date
                    )
                }
            }

            is AddEditTodoEvent.OnDeadlineTimeChange -> {
                _todoState.update {
                    it.copy(
                        currentTodoDeadlineTime = event.time
                    )
                }
            }

            AddEditTodoEvent.HidePriorityDropDownMenu -> {
                _todoState.update {
                    it.copy(
                        isPriorityDropDownMenuExpanded = false
                    )
                }
            }

            AddEditTodoEvent.ShowPriorityDropDownMenu -> {
                _todoState.update {
                    it.copy(
                        isPriorityDropDownMenuExpanded = true
                    )
                }
            }

            is AddEditTodoEvent.OnPriorityDropDownMenuExpandedChange -> {
                _todoState.update {
                    it.copy(
                        isPriorityDropDownMenuExpanded = event.expanded
                    )
                }
            }

            is AddEditTodoEvent.OnCategoryDropDownMenuExpandedChange -> {
                _todoState.update {
                    it.copy(
                        isCategoryDropDownMenuExpanded = event.expanded
                    )
                }
            }

            AddEditTodoEvent.HideCategoryDropDownMenu -> {
                _todoState.update {
                    it.copy(
                        isCategoryDropDownMenuExpanded = false
                    )
                }
            }

            is AddEditTodoEvent.OnCategoryChange -> {
                _todoState.update {
                    it.copy(
                        currentTodoCategory = event.category
                    )
                }
            }
        }
    }

    fun getTodoById(id: Int) {
        viewModelScope.launch {
            val todo = todoUseCases.getTodo(id)
            todo?.let {
                _todoState.update {
                    it.copy(
                        currentTodoId = id,
                        currentTodoTitle = todo.title,
                        currentTodoDeadlineDate = todo.deadlineDate,
                        currentTodoDeadlineTime = todo.deadlineTime,
                        currentTodoPriority = todo.priority,
                        currentTodoDescription = todo.description ?: "",
                        isCurrentTodoDone = todo.isDone,
                        currentTodoCategory = todo.category
                    )
                }
            }
        }
    }
}
