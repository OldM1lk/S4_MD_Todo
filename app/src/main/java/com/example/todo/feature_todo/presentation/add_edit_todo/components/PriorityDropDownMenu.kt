package com.example.todo.feature_todo.presentation.add_edit_todo.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todo.feature_todo.domain.model.Todo
import com.example.todo.feature_todo.presentation.add_edit_todo.AddEditTodoEvent
import com.example.todo.feature_todo.presentation.add_edit_todo.AddEditTodoViewModel

@Composable
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
fun PriorityDropDownMenu(
    viewModel: AddEditTodoViewModel = hiltViewModel()
) {

    val todoState = viewModel.todoState.collectAsStateWithLifecycle()

    ExposedDropdownMenuBox(
        expanded = todoState.value.isPriorityDropDownMenuExpanded,
        onExpandedChange = {
            viewModel.onEvent(AddEditTodoEvent.OnPriorityDropDownMenuExpandedChange(it))
        },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = todoState.value.currentTodoPriority,
            onValueChange = {},
            readOnly = true,
            label = { Text("Priority") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = todoState.value.isPriorityDropDownMenuExpanded
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = todoState.value.isPriorityDropDownMenuExpanded,
            onDismissRequest = {
                viewModel.onEvent(AddEditTodoEvent.HidePriorityDropDownMenu)
            }
        ) {
            Todo.todoPriorities.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    onClick = {
                        viewModel.onEvent(AddEditTodoEvent.OnPriorityChange(it))
                        viewModel.onEvent(AddEditTodoEvent.HidePriorityDropDownMenu)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}