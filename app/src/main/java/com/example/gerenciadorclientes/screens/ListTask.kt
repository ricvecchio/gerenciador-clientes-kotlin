package com.example.gerenciadorclientes.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gerenciadorclientes.base.Routes

@Composable
fun ListTaskScreen(paddingValues: PaddingValues, navController: NavController, viewModel: ListTaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    val showDialog by viewModel.showDialog.collectAsState()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.setShowDialog(false) },
                confirmButton = {
                    Button(onClick = { viewModel.confirmDelete() }) {
                        Text(text = "Sim")
                    }
                },
                dismissButton = {
                    Button(onClick = { viewModel.setShowDialog(false) }) {
                        Text(text = "Não")
                    }
                },
                text = { Text(text = "Tem certeza que deseja remover esta tarefa?") },
                title = { Text(text = "Task Manager") }
            )
        }

        if (tasks.isNotEmpty()) {
            tasks.forEach { (title, description) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = title)
                            Text(text = description, fontSize = 12.sp)
                        }
                        Row {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                    viewModel.setShowDialog(true, title)
                                }
                            )
                        }
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .height(350.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "Registros não localizados",
                    textAlign = TextAlign.Center
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(onClick = {
                navController.navigate(Routes.TaskCreate.route)
            }) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
        }
    }
}


