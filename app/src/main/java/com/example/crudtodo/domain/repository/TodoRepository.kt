package com.example.crudtodo.domain.repository

import com.example.crudtodo.data.local.TodoItem

interface TodoRepository {

    suspend fun getLocalTodos(): List<TodoItem>

    suspend fun insertTodo(todoItem: TodoItem)

    suspend fun getApiTodos(): List<TodoItem>

    suspend fun deleteTodo(todoItem: TodoItem)

    suspend fun updateCompletionState(todoId: Int, newState: Boolean)

}