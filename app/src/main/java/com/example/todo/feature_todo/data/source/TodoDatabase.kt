package com.example.todo.feature_todo.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.feature_todo.domain.model.Todo

@Database(
    entities = [Todo::class],
    version = 1
)
abstract class TodoDatabase : RoomDatabase() {

    abstract val todoDao: TodoDao

    companion object {
        const val DATABASE_NAME = "todos_db"
    }
}