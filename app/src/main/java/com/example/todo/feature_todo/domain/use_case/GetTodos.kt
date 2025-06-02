package com.example.todo.feature_todo.domain.use_case

import com.example.todo.feature_todo.domain.model.Todo
import com.example.todo.feature_todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetTodos(
    private val repository: TodoRepository
) {

    operator fun invoke(): Flow<List<Todo>> {
        return repository.getTodos()
    }
}