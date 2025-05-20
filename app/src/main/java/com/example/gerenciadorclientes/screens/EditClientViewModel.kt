package com.example.gerenciadorclientes.screens

import androidx.lifecycle.ViewModel
import com.example.gerenciadorclientes.model.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EditClientViewModel(val localData: SharedPreferences) : ViewModel() {
    private var _client = MutableStateFlow<SharedPreferences.Client?>(null)
    val client: StateFlow<SharedPreferences.Client?> = _client

    val name = MutableStateFlow("")
    val email = MutableStateFlow("")
    val phone = MutableStateFlow("")
    val address = MutableStateFlow("")

    val nameError = MutableStateFlow<String?>(null)
    val emailError = MutableStateFlow<String?>(null)
    val phoneError = MutableStateFlow<String?>(null)
    val addressError = MutableStateFlow<String?>(null)

    fun loadClient(id: String) {
        localData.getClientById(id)?.let { client ->
            _client.value = client
            name.value = client.name
            email.value = client.email
            phone.value = client.phone
            address.value = client.address
        } ?: run {
            _client.value = null
        }
    }

    fun saveClient(onSuccess: () -> Unit): Boolean {
        var hasError = false

        if (name.value.isBlank()) {
            nameError.value = "Nome é obrigatório"
            hasError = true
        } else {
            nameError.value = null
        }

        if (email.value.isBlank()) {
            emailError.value = "Email é obrigatório"
            hasError = true
        } else {
            emailError.value = null
        }

        if (phone.value.isBlank()) {
            phoneError.value = "Telefone é obrigatório"
            hasError = true
        } else {
            phoneError.value = null
        }

        if (address.value.isBlank()) {
            addressError.value = "Endereço é obrigatório"
            hasError = true
        } else {
            addressError.value = null
        }

        if (hasError) return false

        _client.value?.let { currentClient ->
            val updatedClient = currentClient.copy(
                name = name.value,
                email = email.value,
                phone = phone.value,
                address = address.value
            )
            localData.saveClient(updatedClient)
            _client.value = updatedClient
            onSuccess()
        }

        return true
    }
}
