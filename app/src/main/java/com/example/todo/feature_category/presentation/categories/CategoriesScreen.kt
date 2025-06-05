package com.example.todo.feature_category.presentation.categories

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todo.feature_category.presentation.categories.components.CategoryItem
import com.example.todo.feature_todo.presentation.todos.BottomNavItem

@Composable
fun CategoriesScreen(
    navController: NavController,
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    val items = listOf(
        BottomNavItem.Tasks,
        BottomNavItem.Categories
    )
    var selectedItemIndex by remember { mutableStateOf(0) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(CategoriesEvent.AddNew)
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add category")
            }
        },
        bottomBar = {
            BottomNavigation {
                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                        label = { Text(text = item.label) },
                        selected = selectedItemIndex == index,
                        onClick = {
                            if (selectedItemIndex != index) {
                                selectedItemIndex = index
                                navController.navigate(item.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .statusBarsPadding()
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.value.categories) { category ->
                CategoryItem(
                    category = category,
                    onNameChange = { id, newName ->
                        viewModel.onEvent(CategoriesEvent.UpdateName(id, newName))
                    },
                    onSave = {
                        viewModel.onEvent(CategoriesEvent.Save(category))
                    },
                    onDelete = {
                        viewModel.onEvent(CategoriesEvent.Delete(category))
                    }
                )
            }
        }
    }
}