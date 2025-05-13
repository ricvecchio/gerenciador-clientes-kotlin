package com.example.gerenciadorclientes.screens

import androidx.lifecycle.ViewModel
import com.example.gerenciadorclientes.model.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CreateTaskViewModel(val localData: SharedPreferences) : ViewModel() {
    private var _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private var _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    fun saveTask() {
        val tasks = localData.getTasks().toMutableList()
        tasks.add(_title.value to _description.value)
        localData.saveTasks(tasks)
    }

    fun setTitle(value: String) {
        _title.value = value
    }

    fun setDescription(value: String) {
        _description.value = value
    }
}
