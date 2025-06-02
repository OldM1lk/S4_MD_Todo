package com.example.todo.feature_todo.domain.use_case

import com.example.todo.feature_todo.domain.model.InvalidTodoException
import com.example.todo.feature_todo.domain.model.Todo
import com.example.todo.feature_todo.domain.repository.TodoRepository

class AddTodo(
    private val repository: TodoRepository
) {

    @Throws(InvalidTodoException::class)
    suspend operator fun invoke(todo: Todo) {
        if (todo.title.isBlank()) {
            throw InvalidTodoException("The title of the todo can't be empty.")
        }
        repository.upsertTodo(todo)
    }
}