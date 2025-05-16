package com.example.gerenciadorclientes.base

sealed class Routes(val route: String) {
    data object ClientList : Routes("clientList")
    data object ClientCreate : Routes("clientCreate")

    data object ClientEdit : Routes("clientEdit/{clientId}") {
        fun createRoute(clientId: String) = "clientEdit/$clientId"
    }

    data object ClientDetails : Routes("clientDetails/{clientId}") {
        fun createRoute(clientId: String) = "clientDetails/$clientId"
    }
}
