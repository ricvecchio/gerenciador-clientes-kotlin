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

    fun saveClient() {
        _client.value?.let { currentClient ->
            val updatedClient = currentClient.copy(
                name = name.value,
                email = email.value,
                phone = phone.value,
                address = address.value
            )
            localData.saveClient(updatedClient)
            _client.value = updatedClient
        }
    }
}