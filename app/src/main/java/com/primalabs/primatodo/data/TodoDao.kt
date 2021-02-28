package com.primalabs.primatodo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getAll(): Flow<List<Todo>>

    @Update
    suspend fun update(todo: Todo)

    @Insert
    suspend fun add(todo: Todo): Long

    @Delete
    suspend fun delete(todo: Todo)
}