package com.example.todo.feature_category.domain.use_case

data class CategoryUseCases(
    val getCategory: GetCategory,
    val getCategories: GetCategories,
    val saveCategory: SaveCategory,
    val deleteCategory: DeleteCategory,
)