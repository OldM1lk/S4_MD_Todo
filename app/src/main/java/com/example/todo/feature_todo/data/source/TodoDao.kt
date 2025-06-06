package com.example.todo.feature_todo.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.todo.feature_todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Upsert
    suspend fun upsertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todo WHERE category = :category")
    fun getTodosByCategory(category: String): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id: Int): Todo?

    @Query("SELECT * FROM todo")
    fun getTodos(): Flow<List<Todo>>
}