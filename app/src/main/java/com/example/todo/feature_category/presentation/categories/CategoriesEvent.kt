package com.example.todo.feature_category.presentation.categories

import com.example.todo.feature_category.domain.model.Category

sealed class CategoriesEvent {
    object AddNew : CategoriesEvent()

    data class UpdateName(val id: Int, val name: String) : CategoriesEvent()
    data class Save(val category: Category) : CategoriesEvent()
    data class Delete(val category: Category) : CategoriesEvent()
}