package com.example.todo.feature_category.domain.use_case

import com.example.todo.feature_category.domain.model.Category
import com.example.todo.feature_category.domain.repository.CategoryRepository

class GetCategory(
    private val repository: CategoryRepository
) {

    suspend operator fun invoke(id: Int): Category? {
        return repository.getCategoryById(id)
    }
}