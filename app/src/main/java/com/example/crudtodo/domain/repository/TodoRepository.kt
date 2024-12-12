package com.example.crudtodo.domain.repository

import com.example.crudtodo.data.local.TodoItem

interface TodoRepository {

    fun getLocalTodos(): List<TodoItem>

    suspend fun insertTodo(todoItem: TodoItem)

    suspend fun getApiTodos(): List<TodoItem>

}