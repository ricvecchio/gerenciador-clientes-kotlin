package com.example.gerenciadorclientes.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gerenciadorclientes.model.SharedPreferences

class CreateClientViewModel(private val localData: SharedPreferences) : ViewModel() {

    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _phone = mutableStateOf("")
    val phone: State<String> = _phone

    private val _address = mutableStateOf("")
    val address: State<String> = _address

    private val _nameError = mutableStateOf<String?>(null)
    val nameError: State<String?> = _nameError

    private val _emailError = mutableStateOf<String?>(null)
    val emailError: State<String?> = _emailError

    private val _phoneError = mutableStateOf<String?>(null)
    val phoneError: State<String?> = _phoneError

    private val _addressError = mutableStateOf<String?>(null)
    val addressError: State<String?> = _addressError

    fun setName(value: String) {
        _name.value = value
        _nameError.value = null
    }

    fun setEmail(value: String) {
        _email.value = value
        _emailError.value = null
    }

    fun setPhone(value: String) {
        _phone.value = value
        _phoneError.value = null
    }

    fun setAddress(value: String) {
        _address.value = value
        _addressError.value = null
    }

    fun saveClient(onSuccess: () -> Unit): Boolean {
        var hasError = false

        if (_name.value.isBlank()) {
            _nameError.value = "Nome é obrigatório"
            hasError = true
        }
        if (_email.value.isBlank()) {
            _emailError.value = "Email é obrigatório"
            hasError = true
        }
        if (_phone.value.isBlank()) {
            _phoneError.value = "Telefone é obrigatório"
            hasError = true
        }
        if (_address.value.isBlank()) {
            _addressError.value = "Endereço é obrigatório"
            hasError = true
        }

        if (hasError) return false

        val client = SharedPreferences.Client(
            id = localData.getNextId(),
            name = _name.value,
            email = _email.value,
            phone = _phone.value,
            address = _address.value
        )

        localData.saveClient(client)
        onSuccess()
        return true
    }
}
