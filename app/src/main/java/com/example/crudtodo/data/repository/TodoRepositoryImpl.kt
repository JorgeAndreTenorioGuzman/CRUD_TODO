package com.example.crudtodo.data.repository

import com.example.crudtodo.data.local.TodoDao
import com.example.crudtodo.data.local.TodoItem
import com.example.crudtodo.data.remote.ApiService
import com.example.crudtodo.data.remote.TodoApiService
import com.example.crudtodo.domain.repository.TodoRepository

class TodoRepositoryImpl(
    private val dao: TodoDao,
): TodoRepository {
    override suspend fun getLocalTodos(): List<TodoItem> {
        return dao.getTodos()
    }

    override suspend fun insertTodo(todoItem: TodoItem) {
        dao.insertTodo(todoItem)
    }

    override suspend fun getApiTodos(): List<TodoItem> {
        return ApiService.todoApiService.getTodos()
    }

    override suspend fun deleteTodo(todoItem: TodoItem) {
        dao.deleteTodo(todoItem)
    }

    override suspend fun updateCompletionState(todoId: Int, newState: Boolean) {
        dao.updateCompletionState(todoId, newState)
    }
}