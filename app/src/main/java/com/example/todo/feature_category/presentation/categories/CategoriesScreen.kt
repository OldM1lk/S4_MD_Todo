package com.example.todo.feature_category.presentation.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todo.feature_category.presentation.categories.components.CategoryCard
import com.example.todo.feature_todo.presentation.todos.components.tabItems
import com.example.todo.feature_todo.presentation.util.Screen
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.ui.Alignment

@Composable
fun CategoriesScreen(
    navController: NavController,
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    var selectedTabIndex by remember {
        mutableIntStateOf(1)
    }
    val categories = listOf("All", "Work", "Home", "Study", "Health", "Shopping", "Personal", "Finance")
    val pagerState = rememberPagerState {
        tabItems.size
    }

    LazyColumn(modifier = Modifier.padding(top = 64.dp)) {
        item {
            Text(
                text = "Categories",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        items(categories) { categoryName ->
            CategoryCard(
                category = categoryName.toString(),
                selected = false
            ) {
                when (categoryName) {
                    "All" -> navController.navigate(Screen.Todos)
                    else -> navController.navigate(Screen.FilteredTodos(categoryName))
                }
            }
        }
    }

    Column(modifier = Modifier.statusBarsPadding()) {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                        when (index) {
                            0 -> navController.navigate(Screen.Todos)
                            1 -> navController.navigate(Screen.Categories)
                        }
                    },
                    text = {
                        Text(text = item.title)
                    },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedTabIndex) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = tabItems[index].title)
            }
        }
    }
}