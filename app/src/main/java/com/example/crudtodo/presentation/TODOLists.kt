package com.example.crudtodo.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.crudtodo.presentation.util.Screen

@Composable
fun TODOListsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: TodoViewModel = hiltViewModel()
) {

    val localTodos = viewModel.localTodos.collectAsState(initial = emptyList()).value
    val remoteTodos = viewModel.remoteTodos.collectAsState(initial = emptyList()).value

    Scaffold(
        bottomBar = { BottomAppBar {
            Text(text = "Create Todo",modifier = Modifier.clickable { navController.navigate(Screen.createTodoScreen.route) })
            Text(text = "TODO lists", modifier = Modifier.clickable { navController.navigate(Screen.todoListsScreen.route)  })
        }
        }
    ) { paddingValues ->

        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ){
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (localTodos.isEmpty()) {
                    item {
                        Text("No local todos found!")
                    }
                } else{
                    items(localTodos) { todo ->
                        Card {
                            Column {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    modifier = Modifier.clickable {viewModel.deleteTodo(todo)},
                                    contentDescription = "delete todo"
                                )
                                Row {
                                    Text(text = todo.title, modifier = Modifier.weight(1f))
                                    Text(
                                        text = "${todo.completed}",
                                        modifier = Modifier
                                            .weight(1f)
                                            .clickable {viewModel.updateCompletionState(todo) }
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(remoteTodos) { todo ->
                    Card {
                        Row {
                            Text(text = todo.title, modifier = Modifier.weight(1f))
                            Text(text = "${todo.completed}", modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

            }
        }
    }
}