package com.example.todo.feature_todo.presentation.todos.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todo.feature_todo.domain.model.Todo
import com.example.todo.feature_todo.presentation.todos.TodosEvent

@Composable
fun TodoItem(
    todo: Todo,
    onEvent: (TodosEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = todo.priority.toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = todo.deadline.toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            Checkbox(
                checked = todo.isDone,
                onCheckedChange = {
                    onEvent(TodosEvent.OnDoneChange(todo, it))
                }
            )
        }
    }
}