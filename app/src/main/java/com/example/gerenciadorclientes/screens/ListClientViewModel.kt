package com.example.gerenciadorclientes.screens

import androidx.lifecycle.ViewModel
import com.example.gerenciadorclientes.model.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ListClientViewModel(val localData: SharedPreferences) : ViewModel() {
    private val _clients = MutableStateFlow(localData.getClients())
    val clients: StateFlow<List<SharedPreferences.Client>> = _clients

    private var _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    private var _clientToDelete = MutableStateFlow<String?>(null)

    fun refreshClients() {
        _clients.value = localData.getClients()
    }

    fun deleteClient(id: String) {
        localData.deleteClient(id)
        refreshClients()
    }

    fun setShowDialog(state: Boolean, id: String? = null) {
        _showDialog.value = state
        _clientToDelete.value = id
    }

    fun confirmDelete() {
        _clientToDelete.value?.let { deleteClient(it) }
        setShowDialog(false)
    }
}