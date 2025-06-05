package com.example.todo.feature_category.presentation.util

import com.example.todo.feature_category.domain.model.Category

data class CategoriesState(
    val categories: List<Category> = emptyList()
)