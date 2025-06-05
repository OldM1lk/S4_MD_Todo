package com.example.todo.feature_category.domain.repository

import com.example.todo.feature_category.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun upsertCategory(category: Category)

    suspend fun deleteCategory(category: Category)

    suspend fun getCategoryById(id: Int): Category?

    fun getCategories(): Flow<List<Category>>

}