package com.example.gerenciadorclientes.base

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.gerenciadorclientes.screens.CreateTaskScreen
import com.example.gerenciadorclientes.screens.CreateTaskViewModel
import com.example.gerenciadorclientes.screens.EditTaskScreen
import com.example.gerenciadorclientes.screens.EditTaskViewModel
import com.example.gerenciadorclientes.screens.ListTaskScreen
import com.example.gerenciadorclientes.screens.ListTaskViewModel
import com.example.gerenciadorclientes.model.SharedPreferences

class CallScaffold (val navController: NavController, val localData: SharedPreferences) {

    val createTaskViewModel = CreateTaskViewModel(localData)
    val editTaskViewModel = EditTaskViewModel(localData)
    val listTaskViewModel = ListTaskViewModel(localData)

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CreateScreen (screen: String) {
        Scaffold(
            topBar = {
                when (screen) {
                    Routes.TaskList.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Lista de Tarefas") },
                        )
                    }
                    Routes.TaskCreate.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Criar Tarefa") },
                            navigationIcon = { IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                            } }
                        )
                    }
                    Routes.TaskEdit.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Editar Tarefa") },
                            navigationIcon = { IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                            } }
                        )
                    }
                }
            }
        ) { padding ->
            when (screen) {
                Routes.TaskList.route -> ListTaskScreen(padding, navController = navController, viewModel = listTaskViewModel)
                Routes.TaskCreate.route -> CreateTaskScreen(padding, navController = navController, viewModel = createTaskViewModel)
                Routes.TaskEdit.route -> EditTaskScreen(padding, navController = navController, viewModel = editTaskViewModel)
            }
        }
    }
}