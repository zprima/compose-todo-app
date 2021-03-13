package com.primalabs.primatodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    val title: String,
    val priorityColorHex: String,
    var completed: Boolean
)