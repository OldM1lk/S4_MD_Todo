package com.example.todo.feature_category.domain.use_case

import com.example.todo.feature_category.domain.model.Category
import com.example.todo.feature_category.domain.model.InvalidCategoryException
import com.example.todo.feature_category.domain.repository.CategoryRepository

class SaveCategory(
    private val repository: CategoryRepository
) {

    @Throws(InvalidCategoryException::class)
    suspend operator fun invoke(category: Category) {
        if (category.name.isBlank()) {
            throw InvalidCategoryException("The name of the category can't be empty.")
        }
        repository.upsertCategory(category)
    }
}