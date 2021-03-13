package com.primalabs.primatodo.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.primalabs.primatodo.data.Todo
import com.primalabs.primatodo.ui.viewmodels.ListTodoViewModel
import kotlinx.coroutines.delay
import kotlin.math.floor
import kotlinx.coroutines.launch

@Composable
fun ListTodoScreen(navController: NavController, listTodoViewModel: ListTodoViewModel) {
    val todos = listTodoViewModel.todos.collectAsState(initial = listOf())
    val composableScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                },
                elevation = 0.dp,
            )
        },
        content = {
            Column(modifier = Modifier.padding(16.dp)){
                Text(text = "What's up, Prima?", style = MaterialTheme.typography.h1, color = MaterialTheme.colors.onBackground)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Today's tasks".capitalize(), style = MaterialTheme.typography.caption)
                Spacer(modifier = Modifier.height(16.dp))
                TaskList(todos.value, onCompleted = { composableScope.launch { delay(2000); listTodoViewModel.markAsCompleted(it) } })
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screens.AddTodoScreen.screenName) }) {
               Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TaskList(todos: List<Todo>, onCompleted: (item: Todo) -> Unit) {
    LazyColumn(){
        items(todos) { item ->
            TaskListItem(item, onCompleted = onCompleted)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun TaskListItem(item: Todo, onCompleted: (item: Todo) -> Unit){
    var checked by remember(item.uid) { mutableStateOf(false)}

    AnimatedVisibility(visible = !checked, exit = fadeOut( animationSpec = tween(durationMillis = 1000))) {
        Surface(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(), shape = RoundedCornerShape(25)
        ) {
            Row(modifier = Modifier.padding(16.dp)) {

                TodoCheckbox(
                    checked = checked,
                    onCheckChange = { checked = !checked; onCompleted(item) })

                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }



}

@Composable
fun TodoCheckbox(checked: Boolean, onCheckChange: () -> Unit){
    val colorSecondary = MaterialTheme.colors.secondary

    Box(
        modifier = Modifier
            .size(20.dp)
            .clickable { onCheckChange() }
    ) {
        Canvas(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
                .requiredSize(20.dp)
        ) {
            val strokeWidthPx = floor(2.dp.toPx())

            drawCircle(
                color = colorSecondary,
                style = if (checked) Fill else Stroke(width = strokeWidthPx)
            )
        }

        if(checked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Check",
                tint = MaterialTheme.colors.onSecondary
            )
        }
    }
}

