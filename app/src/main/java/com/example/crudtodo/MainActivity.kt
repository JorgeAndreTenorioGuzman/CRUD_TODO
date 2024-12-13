package com.example.crudtodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crudtodo.presentation.CreateTodo
import com.example.crudtodo.presentation.TODOListsScreen
import com.example.crudtodo.presentation.util.Screen
import com.example.crudtodo.ui.theme.CRUDTODOTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CRUDTODOTheme {
                SetupNavGraph()
            }
        }
    }
}

@Composable
fun SetupNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.todoListsScreen.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.todoListsScreen.route ){
            TODOListsScreen(navController = navController)
        }
        composable(route = Screen.createTodoScreen.route){
            CreateTodo(navController = navController)
        }
    }

}