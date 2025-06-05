package com.example.todo.feature_category.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String
)

class InvalidCategoryException(message: String) : Exception(message)