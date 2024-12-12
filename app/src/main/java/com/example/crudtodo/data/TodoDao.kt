package com.example.crudtodo.data

import androidx.room.Query

interface TodoDao {

    @Query("Select * FROM todos")
    fun getTodos(): List<TodoItem>
}