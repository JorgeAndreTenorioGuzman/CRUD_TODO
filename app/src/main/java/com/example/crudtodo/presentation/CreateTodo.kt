package com.example.crudtodo.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.crudtodo.presentation.util.Screen

@Composable
fun CreateTodo(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: TodoViewModel = hiltViewModel()
) {
    val titleState = viewModel.todoItemTitle.value


    Scaffold(
        bottomBar = { BottomAppBar {
            Text(text = "Create Todo",modifier = Modifier.clickable { navController.navigate(Screen.createTodoScreen.route) })
            Text(text = "TODO lists", modifier = Modifier.clickable { navController.navigate(Screen.todoListsScreen.route)  })
        }}
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ){
            TextField(value = titleState, onValueChange = {viewModel.addTitle(it)})
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { viewModel.addTodoToLocal()}) {
                Text(text = "Add Todo")
            }
        }


    }

}