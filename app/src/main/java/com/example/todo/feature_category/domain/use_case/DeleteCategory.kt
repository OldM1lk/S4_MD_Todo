package com.example.todo.feature_category.domain.use_case

import com.example.todo.feature_category.domain.model.Category
import com.example.todo.feature_category.domain.repository.CategoryRepository

class DeleteCategory(
    private val repository: CategoryRepository
) {

    suspend operator fun invoke(category: Category) {
        repository.deleteCategory(category)
    }
}