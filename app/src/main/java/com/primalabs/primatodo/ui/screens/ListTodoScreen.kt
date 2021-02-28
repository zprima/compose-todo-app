package com.primalabs.primatodo.ui.screens

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.primalabs.primatodo.data.Todo
import com.primalabs.primatodo.ui.viewmodels.ListTodoViewModel



@Composable
fun ListTodoScreen(navController: NavController, listTodoViewModel: ListTodoViewModel) {
    val todos = listTodoViewModel.todos.collectAsState(initial = listOf())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Todo") },
            )
        },
        content = {
            Column() {
                ListTodos(todos = todos.value)

                Spacer(modifier = Modifier.weight(1f))

                Row(horizontalArrangement = Arrangement.End, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)){
                    FloatingActionButton(onClick = { navController.navigate(SCREENS.ADD_TODO_SCREEN.screenName) }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListTodos(todos: List<Todo>?){
    if(todos.isNullOrEmpty()){
        return
    }

    LazyColumn{
        items(todos){ item ->
            ListItem(text = { Text(text = item.title)} )
        }
    }
}