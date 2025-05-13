package com.example.gerenciadorclientes.base

sealed class Routes(val route: String) {
    data object TaskList: Routes("taskList")
    data object TaskCreate: Routes("taskCreate")
    data object TaskEdit: Routes("taskEdit")
}