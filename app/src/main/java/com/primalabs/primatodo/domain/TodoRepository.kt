package com.primalabs.primatodo.domain

import com.primalabs.primatodo.data.Todo
import com.primalabs.primatodo.data.TodoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
){
    fun getAll() = todoDao.getAll()

    suspend fun add(todo: Todo) = todoDao.add(todo)

    suspend fun delete(todo: Todo) = todoDao.delete(todo)
}