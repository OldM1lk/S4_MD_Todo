package com.example.todo.feature_todo.domain.use_case

data class TodoUseCases(
    val addTodo: AddTodo,
    val deleteTodo: DeleteTodo,
    val getTodo: GetTodo,
    val getTodos: GetTodos
)
