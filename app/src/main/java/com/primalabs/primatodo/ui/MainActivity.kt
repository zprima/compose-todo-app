package com.primalabs.primatodo.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.primalabs.primatodo.ui.theme.PrimaTodoTheme
import com.primalabs.primatodo.ui.screens.AddTodoScreen
import com.primalabs.primatodo.ui.screens.ListTodoScreen
import com.primalabs.primatodo.ui.screens.SCREENS
import com.primalabs.primatodo.ui.viewmodels.AddTodoViewModel
import com.primalabs.primatodo.ui.viewmodels.ListTodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoApp()
        }
    }
}

@Composable
fun TodoApp() {
    val navController = rememberNavController()

    PrimaTodoTheme(darkTheme = false) {
        NavHost(navController, startDestination = SCREENS.LIST_TODO_SCREEN.screenName) {
            composable(SCREENS.LIST_TODO_SCREEN.screenName) { backStackEntry ->

                val listTodoViewModel: ListTodoViewModel = viewModel(factory =
                    HiltViewModelFactory(context = LocalContext.current, navBackStackEntry = backStackEntry)
                )

                ListTodoScreen(navController = navController, listTodoViewModel = listTodoViewModel)
            }
            composable(SCREENS.ADD_TODO_SCREEN.screenName) { backStackEntry ->

                val addTodoViewModel: AddTodoViewModel = viewModel(factory =
                    HiltViewModelFactory(context = LocalContext.current, navBackStackEntry = backStackEntry)
                )

                AddTodoScreen(navController = navController, addTodoViewModel = addTodoViewModel)
            }
        }
    }
}

