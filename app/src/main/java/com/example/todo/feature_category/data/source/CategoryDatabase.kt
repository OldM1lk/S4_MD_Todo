package com.example.todo.feature_category.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.feature_category.domain.model.Category

@Database(
    entities = [Category::class],
    version = 1
)
abstract class CategoryDatabase : RoomDatabase() {

    abstract val categoryDao: CategoryDao

    companion object {
        const val DATABASE_NAME = "categories_db"
    }
}