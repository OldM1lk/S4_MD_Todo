package com.example.todo.di

import android.app.Application
import androidx.room.Room
import com.example.todo.feature_category.data.repository.CategoryRepositoryImpl
import com.example.todo.feature_category.data.source.CategoryDao
import com.example.todo.feature_category.data.source.CategoryDatabase
import com.example.todo.feature_category.domain.repository.CategoryRepository
import com.example.todo.feature_category.domain.use_case.CategoryUseCases
import com.example.todo.feature_category.domain.use_case.DeleteCategory
import com.example.todo.feature_category.domain.use_case.GetCategories
import com.example.todo.feature_category.domain.use_case.GetCategory
import com.example.todo.feature_category.domain.use_case.SaveCategory
import com.example.todo.feature_todo.data.repository.TodoRepositoryImpl
import com.example.todo.feature_todo.data.source.TodoDatabase
import com.example.todo.feature_todo.domain.repository.TodoRepository
import com.example.todo.feature_todo.domain.use_case.AddTodo
import com.example.todo.feature_todo.domain.use_case.DeleteTodo
import com.example.todo.feature_todo.domain.use_case.GetTodo
import com.example.todo.feature_todo.domain.use_case.GetTodos
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
            getTodos = GetTodos(repository)
        )
    }

    // --- Category ---

    @Provides
    @Singleton
    fun provideCategoryDatabase(app: Application): CategoryDatabase {
        return Room.databaseBuilder(
            app,
            CategoryDatabase::class.java,
            CategoryDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(database: CategoryDatabase): CategoryDao {
        return database.categoryDao
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(dao: CategoryDao): CategoryRepository {
        return CategoryRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideCategoryUseCases(repository: CategoryRepository): CategoryUseCases {
        return CategoryUseCases(
            saveCategory = SaveCategory(repository),
            deleteCategory = DeleteCategory(repository),
            getCategory = GetCategory(repository),
            getCategories = GetCategories(repository)
        )
    }
}