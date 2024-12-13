package com.example.crudtodo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoItem(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val completed: Boolean = false
)
