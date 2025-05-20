package com.example.gerenciadorclientes.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun EditClientScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: EditClientViewModel
) {
    val context = LocalContext.current

    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val phone by viewModel.phone.collectAsState()
    val address by viewModel.address.collectAsState()

    val nameError by viewModel.nameError.collectAsState()
    val emailError by viewModel.emailError.collectAsState()
    val phoneError by viewModel.phoneError.collectAsState()
    val addressError by viewModel.addressError.collectAsState()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { viewModel.name.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nome") },
            isError = nameError != null
        )
        nameError?.let {
            Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.email.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = emailError != null
        )
        emailError?.let {
            Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { viewModel.phone.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Telefone") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            isError = phoneError != null
        )
        phoneError?.let {
            Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = address,
            onValueChange = { viewModel.address.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Endereço") },
            maxLines = 3,
            isError = addressError != null
        )
        addressError?.let {
            Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val success = viewModel.saveClient {
                    navController.popBackStack()
                    Toast.makeText(context, "Cliente atualizado com sucesso!", Toast.LENGTH_SHORT)
                        .show()
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
            Text("Salvar Alterações")
        }
    }
}

