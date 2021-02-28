package com.primalabs.primatodo.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.primalabs.primatodo.data.Todo
import com.primalabs.primatodo.domain.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel(){

    var saveComplete = MutableLiveData<Boolean>(false)

    suspend fun Add(title: String, priorityColorHex: String){
        val todo: Todo = Todo(uid = 0, title = title, priorityColorHex = priorityColorHex, completed = false)
        val id = repository.add(todo = todo)
        Log.d("mijagi", "$id")

        saveComplete.value = true
    }
}