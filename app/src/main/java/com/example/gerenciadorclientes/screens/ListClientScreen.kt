package com.example.gerenciadorclientes.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gerenciadorclientes.base.Routes

@Composable
fun ListClientScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: ListClientViewModel
) {
    val clients by viewModel.clients.collectAsState()
    val showDialog by viewModel.showDialog.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { viewModel.setShowDialog(false) },
                    title = { Text("Confirmar Exclusão") },
                    text = { Text("Tem certeza que deseja excluir este cliente?") },
                    confirmButton = {
                        Button(onClick = { viewModel.confirmDelete() }) {
                            Text("Sim")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { viewModel.setShowDialog(false) }) {
                            Text("Não")
                        }
                    }
                )
            }

            if (clients.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Nenhum cliente cadastrado")
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(clients) { client ->
                        ClientCard(
                            client = client,
                            onEdit = {
                                navController.navigate(Routes.ClientEdit.createRoute(client.id))
                            },
                            onDelete = {
                                viewModel.setShowDialog(true, client.id)
                            },
                            onClick = {
                                navController.navigate(Routes.ClientDetails.createRoute(client.id))
                            }
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { navController.navigate(Routes.ClientCreate.route) },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Adicionar Cliente")
        }
    }
}