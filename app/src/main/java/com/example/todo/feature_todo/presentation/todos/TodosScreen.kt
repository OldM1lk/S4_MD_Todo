package com.example.todo.feature_todo.presentation.todos

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todo.feature_todo.presentation.FAB_EXPLODE_BOUNDS_KEY
import com.example.todo.feature_todo.presentation.todos.components.TodoItem
import com.example.todo.feature_todo.presentation.util.Screen
import kotlinx.coroutines.launch

@Composable
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalSharedTransitionApi::class)
fun SharedTransitionScope.TodosScreen(
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: TodosViewModel = hiltViewModel()
) {
    val todoState = viewModel.todoState.collectAsState()
    val todos = todoState.value.todos.collectAsState(emptyList())
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        Screen.AddEditTodo(
                            id = null
                        )
                    )
                },
                modifier = Modifier.sharedBounds(
                    sharedContentState = rememberSharedContentState(
                        key = FAB_EXPLODE_BOUNDS_KEY
                    ),
                    animatedVisibilityScope = animatedVisibilityScope
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add todo"
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .statusBarsPadding()
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(todos.value) {
                TodoItem(
                    navController = navController,
                    todo = it,
                    onDoneChange = { todo, isDone ->
                        viewModel.onEvent(TodosEvent.OnDoneChange(todo, isDone))
                    },
                    onDeleteTodo = {
                        viewModel.onEvent(TodosEvent.DeleteTodo(it))
                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                message = "Todo deleted",
                                actionLabel = "Undo"
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                viewModel.onEvent(TodosEvent.OnUndoDeleteClick)
                            }
                        }
                    }
                )
            }
        }
    }
}