package com.example.crudtodo.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudtodo.data.local.TodoItem
import com.example.crudtodo.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class TodoViewModel(private val repository: TodoRepository): ViewModel() {

    private val _localTodos = MutableStateFlow<List<TodoItem>>(emptyList())
    val localTodos: StateFlow<List<TodoItem>> = _localTodos

    private val _remoteTodos = MutableStateFlow<List<TodoItem>>(emptyList())
    var remoteTodos: StateFlow<List<TodoItem>> = _remoteTodos

    var todoItemTitle = mutableStateOf("")
        private set


    init {
        getRemoteTodos()
        getLocalTodos()
    }

    fun getRemoteTodos() {
        viewModelScope.launch {
            _remoteTodos.value = repository.getApiTodos()
        }
    }

    fun getLocalTodos() {
        viewModelScope.launch {
            _localTodos.value = repository.getLocalTodos()
        }
    }

    fun addTitle(enteredTitle: String){
        todoItemTitle.value = enteredTitle
    }


    fun addTodoToLocal() {
        val todo = TodoItem(
            title = todoItemTitle.value,
            completed = false
        )
        viewModelScope.launch {
            repository.insertTodo(todo)
        }
    }


}