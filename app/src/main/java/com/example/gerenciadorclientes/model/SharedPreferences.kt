package com.example.gerenciadorclientes.model

import android.content.Context
import androidx.core.content.edit

class SharedPreferences(context: Context) {
    private val preferences: android.content.SharedPreferences =
        context.getSharedPreferences("localData", Context.MODE_PRIVATE)

    fun saveTasks(tasks: List<Pair<String, String>>) {
        val jsonTasks = tasks.joinToString(";") { "${it.first}|${it.second}" }
        preferences.edit {
            putString(Constants.TASK_LIST, jsonTasks).apply()
        }
    }

    fun getTasks(): List<Pair<String, String>> {
        val savedData = preferences.getString(Constants.TASK_LIST, "") ?: ""
        if (savedData.isBlank()) return emptyList()

        return savedData.split(";").map {
            val (title, description) = it.split("|")
            title to description
        }
    }

    fun deleteTask(title: String) {
        val tasks = getTasks().filterNot { it.first == title }
        saveTasks(tasks)
    }
}