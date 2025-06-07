package com.example.todo.feature_todo.domain.use_case

import com.example.todo.feature_todo.domain.model.Todo
import com.example.todo.feature_todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetTodosByCategory(
    private val repository: TodoRepository
) {

    operator fun invoke(category: String): Flow<List<Todo>> {
        return repository.getTodosByCategory(category)
    }
}