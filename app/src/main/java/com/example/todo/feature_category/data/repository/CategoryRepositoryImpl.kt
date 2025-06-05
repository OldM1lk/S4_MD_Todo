package com.example.todo.feature_category.data.repository

import com.example.todo.feature_category.data.source.CategoryDao
import com.example.todo.feature_category.domain.model.Category
import com.example.todo.feature_category.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(
    private val dao: CategoryDao
) : CategoryRepository {

    override suspend fun upsertCategory(category: Category) {
        dao.upsertCategory(category)
    }

    override suspend fun deleteCategory(category: Category) {
        dao.deleteCategory(category)
    }

    override suspend fun getCategoryById(id: Int): Category? {
        return dao.getCategoryById(id)
    }

    override fun getCategories(): Flow<List<Category>> {
        return dao.getCategories()
    }
}