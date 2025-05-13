package com.example.gerenciadorclientes.screens

import androidx.lifecycle.ViewModel
import com.example.gerenciadorclientes.model.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CreateClientViewModel(val localData: SharedPreferences) : ViewModel() {
    private var _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private var _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private var _phone = MutableStateFlow("")
    val phone: StateFlow<String> = _phone

    private var _address = MutableStateFlow("")
    val address: StateFlow<String> = _address

    fun saveClient() {
        val client = SharedPreferences.Client(
            id = localData.getNextId(),
            name = _name.value,
            email = _email.value,
            phone = _phone.value,
            address = _address.value
        )
        localData.saveClient(client)
    }

    fun setName(value: String) { _name.value = value }
    fun setEmail(value: String) { _email.value = value }
    fun setPhone(value: String) { _phone.value = value }
    fun setAddress(value: String) { _address.value = value }
}
