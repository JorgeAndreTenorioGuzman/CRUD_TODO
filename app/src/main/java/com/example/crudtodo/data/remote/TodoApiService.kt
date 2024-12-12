package com.example.crudtodo.data.remote

import com.example.crudtodo.data.local.TodoItem
import retrofit2.http.GET

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<TodoItem>
}