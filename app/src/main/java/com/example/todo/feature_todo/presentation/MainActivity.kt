package com.example.todo.feature_todo.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.todo.feature_category.presentation.categories.CategoriesScreen
import com.example.todo.feature_todo.presentation.add_edit_todo.AddEditTodoScreen
import com.example.todo.feature_todo.presentation.todos.TodosScreen
import com.example.todo.feature_todo.presentation.util.Screen
import com.example.todo.ui.theme.TodoTheme
import dagger.hilt.android.AndroidEntryPoint

const val FAB_EXPLODE_BOUNDS_KEY = "FAB_EXPLODE_BOUNDS_KEY"

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalSharedTransitionApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoTheme {
                val navController = rememberNavController()

                SharedTransitionLayout {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Todos
                    ) {
                        composable<Screen.Todos> {
                            TodosScreen(
                                navController = navController,
                                animatedVisibilityScope = this,
                                initialCategory = null
                            )
                        }
                        composable<Screen.AddEditTodo> {
                            val args = it.toRoute<Screen.AddEditTodo>()
                            AddEditTodoScreen(
                                navController = navController,
                                animatedVisibilityScope = this,
                                todoId = args.id,
                                modifier = Modifier.sharedBounds(
                                    sharedContentState = rememberSharedContentState(
                                        key = FAB_EXPLODE_BOUNDS_KEY
                                    ),
                                    animatedVisibilityScope = this
                                )
                            )
                        }
                        composable<Screen.Categories> {
                            CategoriesScreen(navController = navController)
                        }

                        composable<Screen.FilteredTodos> {
                            val args = it.toRoute<Screen.FilteredTodos>()
                            TodosScreen(
                                navController = navController,
                                animatedVisibilityScope = this,
                                initialCategory = args.category
                            )
                        }
                    }
                }
            }
        }
    }
}