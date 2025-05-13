package com.example.gerenciadorclientes.screens

import androidx.lifecycle.ViewModel
import com.example.gerenciadorclientes.model.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ListTaskViewModel(val localData: SharedPreferences) : ViewModel() {
    private val _tasks = MutableStateFlow(localData.getTasks())
    val tasks: StateFlow<List<Pair<String, String>>> = _tasks

    private var _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    private var _taskToDelete = MutableStateFlow<String?>(null)

    fun deleteTask(title: String) {
        localData.deleteTask(title)
        _tasks.value = localData.getTasks()
    }

    fun setShowDialog(state: Boolean, title: String? = null) {
        _showDialog.value = state
        _taskToDelete.value = title
    }

    fun confirmDelete() {
        _taskToDelete.value?.let { deleteTask(it) }
        setShowDialog(false)
    }
}

