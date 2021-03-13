package com.primalabs.primatodo.ui.screens

import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.primalabs.primatodo.ui.viewmodels.AddTodoViewModel
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@Composable
fun AddTodoScreen(navController: NavController, addTodoViewModel: AddTodoViewModel) {
    var title by remember { mutableStateOf("")}
    val scope = rememberCoroutineScope()

    val shouldNavigateBack = addTodoViewModel.saveComplete.observeAsState(false)
    if(shouldNavigateBack.value){
        navController.popBackStack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add New Todo")},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                },
                elevation = 0.dp
            )
        },
        content = {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(text = "Title")},
                    maxLines = 8,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.9f),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        backgroundColor = MaterialTheme.colors.primary,
                        cursorColor = Color.White,
                        focusedBorderColor = MaterialTheme.colors.primary,
                        focusedLabelColor = Color.White,
                        unfocusedBorderColor = MaterialTheme.colors.primary
                    )
                )

            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch { addTodoViewModel.Add(title = title, priorityColorHex = Color.Green.toString()) }
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        }
    )
}