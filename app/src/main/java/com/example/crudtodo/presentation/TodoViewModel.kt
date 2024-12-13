package com.example.crudtodo.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudtodo.data.local.TodoItem
import com.example.crudtodo.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TodoViewModel @Inject constructor (
    private val repository: TodoRepository
): ViewModel() {

    private val _localTodos = MutableStateFlow<List<TodoItem>>(emptyList())
    val localTodos: StateFlow<List<TodoItem>> = _localTodos

    private val _remoteTodos = MutableStateFlow<List<TodoItem>>(emptyList())
    var remoteTodos: StateFlow<List<TodoItem>> = _remoteTodos

    var todoItemTitle = mutableStateOf("")
        private set


    init {
        viewModelScope.launch(Dispatchers.IO) {
            getRemoteTodos()
            getLocalTodos()
        }
    }

    fun getRemoteTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            val remoteData = repository.getApiTodos()
            _remoteTodos.value = remoteData
        }
    }

    fun getLocalTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            val localData = repository.getLocalTodos()
            _localTodos.value = localData
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
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTodo(todo)
        }
    }

    fun deleteTodo(todoItem: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(todoItem)
            getLocalTodos()
        }
    }

    fun updateCompletionState(todoItem: TodoItem){
        viewModelScope.launch(Dispatchers.IO) {
            val newState = !todoItem.completed
            todoItem.id?.let { repository.updateCompletionState(it, newState) }
            getLocalTodos()

        }
    }


}