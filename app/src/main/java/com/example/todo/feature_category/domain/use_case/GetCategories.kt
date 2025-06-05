package com.example.todo.feature_category.domain.use_case

import com.example.todo.feature_category.domain.repository.CategoryRepository
import com.example.todo.feature_category.domain.model.Category
import kotlinx.coroutines.flow.Flow

class GetCategories (
    private val repository: CategoryRepository
) {

    operator fun invoke(): Flow<List<Category>> {
        return repository.getCategories()
    }
}