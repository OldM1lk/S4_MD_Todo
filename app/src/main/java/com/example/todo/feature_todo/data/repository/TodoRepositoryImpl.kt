package com.example.todo.feature_todo.data.repository

import com.example.todo.feature_todo.data.source.TodoDao
import com.example.todo.feature_todo.domain.model.Todo
import com.example.todo.feature_todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val dao: TodoDao
) : TodoRepository {

    override suspend fun upsertTodo(todo: Todo) {
        dao.upsertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }
}