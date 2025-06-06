package com.example.todo.feature_todo.presentation.add_edit_todo.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todo.feature_todo.presentation.add_edit_todo.AddEditTodoEvent
import com.example.todo.feature_todo.presentation.add_edit_todo.AddEditTodoViewModel

@Composable
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
fun CategoriesDropDownMenu(
    viewModel: AddEditTodoViewModel = hiltViewModel()
) {

    val todoState = viewModel.todoState.collectAsStateWithLifecycle()

    ExposedDropdownMenuBox(
        expanded = todoState.value.isCategoryDropDownMenuExpanded,
        onExpandedChange = {
            viewModel.onEvent(AddEditTodoEvent.OnCategoryDropDownMenuExpandedChange(it))
        },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = todoState.value.currentTodoCategory ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text("Category") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = todoState.value.isPriorityDropDownMenuExpanded
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(
                    MenuAnchorType.PrimaryNotEditable,
                    enabled = true
                )
        )
        ExposedDropdownMenu(
            expanded = todoState.value.isCategoryDropDownMenuExpanded,
            onDismissRequest = {
                viewModel.onEvent(AddEditTodoEvent.HideCategoryDropDownMenu)
            }
        ) {
            viewModel.categories.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    onClick = {
                        viewModel.onEvent(AddEditTodoEvent.OnCategoryChange(it))
                        viewModel.onEvent(AddEditTodoEvent.HideCategoryDropDownMenu)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}