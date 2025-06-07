package com.example.todo.di

import android.app.Application
import androidx.room.Room
import com.example.todo.feature_todo.data.repository.TodoRepositoryImpl
import com.example.todo.feature_todo.data.source.TodoDatabase
import com.example.todo.feature_todo.domain.repository.TodoRepository
import com.example.todo.feature_todo.domain.use_case.AddTodo
import com.example.todo.feature_todo.domain.use_case.DeleteTodo
import com.example.todo.feature_todo.domain.use_case.GetTodo
import com.example.todo.feature_todo.domain.use_case.GetTodos
import com.example.todo.feature_todo.domain.use_case.GetTodosByCategory
import com.example.todo.feature_todo.domain.use_case.TodoUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            TodoDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepository {
        return TodoRepositoryImpl(db.todoDao)
    }

    @Provides
    @Singleton
    fun provideTodoUseCases(repository: TodoRepository): TodoUseCases {
        return TodoUseCases(
            addTodo = AddTodo(repository),
            deleteTodo = DeleteTodo(repository),
            getTodo = GetTodo(repository),
            getTodos = GetTodos(repository),
            getTodosByCategory = GetTodosByCategory(repository)
        )
    }
}