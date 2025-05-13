package com.example.gerenciadorclientes.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun EditTaskScreen(paddingValues: PaddingValues, navController: NavController, viewModel: EditTaskViewModel) {
    val title by viewModel.title.collectAsState()
    val description by viewModel.description.collectAsState()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { viewModel.setTitle(it) },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Título") },
            placeholder = { Text(text = "Digite o Título", Modifier.alpha(0.3f)) }
        )
        OutlinedTextField(
            value = description,
            onValueChange = { viewModel.setDescription(it) },
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f),
            label = { Text(text = "Descrição") },
            placeholder = { Text(text = "Digite a Descrição", Modifier.alpha(0.3f)) }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .weight(1f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = {
                    viewModel.saveTask()
                    navController.popBackStack()
                },
                modifier = Modifier
                    .width(140.dp)
            ) {
                Text(text = "Salvar")
            }
        }
    }
}
