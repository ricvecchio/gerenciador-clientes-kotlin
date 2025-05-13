package com.example.gerenciadorclientes.screens

import androidx.lifecycle.ViewModel
import com.example.gerenciadorclientes.model.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EditTaskViewModel(val localData: SharedPreferences) : ViewModel() {
    private var _tasks = MutableStateFlow(localData.getTasks())
    private var _currentTaskIndex = MutableStateFlow(-1)

    private var _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private var _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    fun loadTask(title: String) {
        _currentTaskIndex.value = _tasks.value.indexOfFirst { it.first == title }
        if (_currentTaskIndex.value >= 0) {
            _title.value = _tasks.value[_currentTaskIndex.value].first
            _description.value = _tasks.value[_currentTaskIndex.value].second
        }
    }

    fun saveTask() {
        if (_currentTaskIndex.value >= 0) {
            val updatedTasks = _tasks.value.toMutableList()
            updatedTasks[_currentTaskIndex.value] = _title.value to _description.value
            localData.saveTasks(updatedTasks)
            _tasks.value = updatedTasks
        }
    }

    fun setTitle(value: String) {
        _title.value = value
    }

    fun setDescription(value: String) {
        _description.value = value
    }
}

