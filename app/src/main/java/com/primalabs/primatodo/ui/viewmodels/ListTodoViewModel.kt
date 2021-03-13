package com.primalabs.primatodo.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.primalabs.primatodo.data.AppDatabase
import com.primalabs.primatodo.data.Todo
import com.primalabs.primatodo.data.TodoDao
import com.primalabs.primatodo.domain.TodoRepository
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ListTodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
): ViewModel() {
    val todos = todoRepository.getAll()

    suspend fun markAsCompleted(todo: Todo){
        todoRepository.delete(todo)
    }
}
