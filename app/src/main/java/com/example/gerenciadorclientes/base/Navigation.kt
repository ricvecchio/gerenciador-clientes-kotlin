package com.example.gerenciadorclientes.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gerenciadorclientes.model.SharedPreferences

class Navigation {
    private lateinit var navController: NavHostController
    private lateinit var localData: SharedPreferences

    @Composable
    fun Create() {
        navController = rememberNavController()
        localData = SharedPreferences(LocalContext.current)

        NavHost(navController = navController, startDestination = Routes.TaskList.route) {
            composable(Routes.TaskList.route) {
                CallScaffold(navController = navController, localData = localData).CreateScreen(screen = Routes.TaskList.route)
            }
            composable(Routes.TaskCreate.route) {
                CallScaffold(navController = navController, localData = localData).CreateScreen(screen = Routes.TaskCreate.route)
            }
            composable(Routes.TaskEdit.route) {
                CallScaffold(navController = navController, localData = localData).CreateScreen(screen = Routes.TaskEdit.route)
            }
        }
    }
}