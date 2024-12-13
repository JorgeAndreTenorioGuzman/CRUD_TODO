package com.example.crudtodo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {

    @Query("Select * FROM todoitem")
    fun getTodos(): List<TodoItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todoItem: TodoItem)

    @Query("UPDATE todoitem SET completed =:newState WHERE id =:todoId")
    suspend fun updateCompletionState(todoId: Int, newState: Boolean)

    @Delete
    suspend fun deleteTodo(todoItem: TodoItem)
}