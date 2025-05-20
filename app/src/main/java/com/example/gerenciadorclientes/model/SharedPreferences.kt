package com.example.gerenciadorclientes.model

import android.content.Context
import androidx.core.content.edit
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SharedPreferences(context: Context) {
    private val preferences: android.content.SharedPreferences =
        context.getSharedPreferences("clientData", Context.MODE_PRIVATE)

    data class Client(
        val id: String,
        val name: String,
        val email: String,
        val phone: String,
        val address: String,
        val registrationDate: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(
            Date()
        )
    )

    fun getNextId(): String {
        val nextId = preferences.getInt(Constants.CLIENT_ID_COUNTER, 1)
        preferences.edit {
            putInt(Constants.CLIENT_ID_COUNTER, nextId + 1)
        }
        return "CLI-$nextId"
    }

    fun saveClient(client: Client) {
        val clients = getClients().toMutableList()
        val existingIndex = clients.indexOfFirst { it.id == client.id }

        if (existingIndex >= 0) {
            clients[existingIndex] = client
        } else {
            clients.add(client)
        }

        saveClients(clients)
    }

    private fun saveClients(clients: List<Client>) {
        val jsonString = JSONArray().apply {
            clients.forEach { client ->
                put(JSONObject().apply {
                    put("id", client.id)
                    put("name", client.name)
                    put("email", client.email)
                    put("phone", client.phone)
                    put("address", client.address)
                    put("registrationDate", client.registrationDate)
                })
            }
        }.toString()

        preferences.edit {
            putString(Constants.CLIENT_LIST, jsonString)
        }
    }

    fun getClients(): List<Client> {
        val jsonString = preferences.getString(Constants.CLIENT_LIST, null) ?: return emptyList()

        return try {
            val jsonArray = JSONArray(jsonString)
            val clients = mutableListOf<Client>()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                clients.add(
                    Client(
                        id = jsonObject.getString("id"),
                        name = jsonObject.getString("name"),
                        email = jsonObject.getString("email"),
                        phone = jsonObject.getString("phone"),
                        address = jsonObject.getString("address"),
                        registrationDate = jsonObject.getString("registrationDate")
                    )
                )
            }

            clients
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun deleteClient(id: String) {
        val clients = getClients().filterNot { it.id == id }
        saveClients(clients)
    }

    fun getClientById(id: String): Client? {
        return getClients().firstOrNull { it.id == id }
    }
}