package com.example.crudtodo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [TodoItem::class],
    version = 1
)
abstract class TodoDatabase: RoomDatabase() {
    abstract val todoDao: TodoDao

    companion object{
        const val DATABASE_NAME = "todo_db"
    }
}