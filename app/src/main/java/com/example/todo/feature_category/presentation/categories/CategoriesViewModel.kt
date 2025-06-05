package com.example.todo.feature_category.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.feature_category.domain.model.Category
import com.example.todo.feature_category.domain.use_case.CategoryUseCases
import com.example.todo.feature_category.presentation.util.CategoriesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val useCases: CategoryUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(CategoriesState())
    val state: StateFlow<CategoriesState> = _state

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            useCases.getCategories().collect { categories ->
                _state.update {
                    it.copy(categories = categories)
                }
            }
        }
    }

    fun onEvent(event: CategoriesEvent) {
        when (event) {
            is CategoriesEvent.AddNew -> {
                _state.update {
                    it.copy(
                        categories = it.categories + Category(name = "")
                    )
                }
            }
            is CategoriesEvent.UpdateName -> {
                _state.update {
                    it.copy(
                        categories = it.categories.map { category ->
                            if (category.id == event.id) category.copy(name = event.name) else category
                        }
                    )
                }
            }
            is CategoriesEvent.Save -> {
                val category = event.category
                if (category.name.isNotBlank()) {
                    viewModelScope.launch {
                        useCases.saveCategory(category)
                    }
                }
            }
            is CategoriesEvent.Delete -> {
                viewModelScope.launch {
                    useCases.deleteCategory(event.category)
                }
                _state.update { state ->
                    state.copy(
                        categories = state.categories.filterNot { it.id == event.category.id }
                    )
                }
            }
        }
    }
}