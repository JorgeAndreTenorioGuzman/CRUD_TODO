package com.example.crudtodo.presentation.util

sealed class Screen(val route: String){
    object createTodoScreen: Screen("create_todo_screen")
    object todoListsScreen: Screen("todo_lists_screen")
}
