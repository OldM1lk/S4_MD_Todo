package com.example.todo.feature_todo.presentation

import android.os.Build
import android.os.Bundle
import android.transition.Scene
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.todo.feature_todo.presentation.add_edit_todo.AddEditTodoScreen
import com.example.todo.feature_todo.presentation.todos.TodosScreen
import com.example.todo.feature_todo.presentation.util.Screen
import com.example.todo.ui.theme.TodoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Todos
                ) {
                    composable<Screen.Todos> {
                        TodosScreen(
                            navController = navController
                        )
                    }
                    composable<Screen.AddEditTodo> {
                        val args = it.toRoute<Screen.AddEditTodo>()
                        AddEditTodoScreen(
                            navController = navController,
                            todoId = args.id
                        )
                    }
                }
            }
        }
    }
}
