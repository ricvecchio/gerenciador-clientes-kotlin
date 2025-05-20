package com.example.gerenciadorclientes.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CreateClientScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: CreateClientViewModel
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(
            value = viewModel.name.value,
            onValueChange = viewModel::setName,
            label = { Text("Nome") },
            isError = viewModel.nameError.value != null,
            modifier = Modifier.fillMaxWidth()
        )
        viewModel.nameError.value?.let {
            Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.email.value,
            onValueChange = viewModel::setEmail,
            label = { Text("Email") },
            isError = viewModel.emailError.value != null,
            modifier = Modifier.fillMaxWidth()
        )
        viewModel.emailError.value?.let {
            Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.phone.value,
            onValueChange = viewModel::setPhone,
            label = { Text("Telefone") },
            isError = viewModel.phoneError.value != null,
            modifier = Modifier.fillMaxWidth()
        )
        viewModel.phoneError.value?.let {
            Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.address.value,
            onValueChange = viewModel::setAddress,
            label = { Text("Endereço") },
            isError = viewModel.addressError.value != null,
            modifier = Modifier.fillMaxWidth()
        )
        viewModel.addressError.value?.let {
            Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val success = viewModel.saveClient {
                    navController.popBackStack()
                    Toast.makeText(context, "Cliente salvo com sucesso!", Toast.LENGTH_SHORT).show()
                }
                if (!success) {
                    Toast.makeText(
                        context,
                        "Por favor, preencha todos os campos obrigatórios",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar")
        }
    }
}
