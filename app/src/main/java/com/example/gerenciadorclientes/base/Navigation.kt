package com.example.gerenciadorclientes.base

import CallScaffold
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gerenciadorclientes.model.SharedPreferences
import com.example.gerenciadorclientes.screens.ClientDetailsScreen
import com.example.gerenciadorclientes.screens.EditClientViewModel

class Navigation {
    private lateinit var navController: NavHostController
    private lateinit var localData: SharedPreferences

    @SuppressLint("ViewModelConstructorInComposable")
    @Composable
    fun Create() {
        navController = rememberNavController()
        localData = SharedPreferences(LocalContext.current)

        NavHost(navController = navController, startDestination = Routes.ClientList.route) {
            composable(Routes.ClientList.route) {
                CallScaffold(navController = navController, localData = localData)
                    .CreateScreen(screen = Routes.ClientList.route)
            }
            composable(Routes.ClientCreate.route) {
                CallScaffold(navController = navController, localData = localData)
                    .CreateScreen(screen = Routes.ClientCreate.route)
            }
            composable(Routes.ClientEdit.route) {
                CallScaffold(navController = navController, localData = localData)
                    .CreateScreen(screen = Routes.ClientEdit.route)
            }
            composable(
                route = Routes.ClientDetails.route,
                arguments = listOf(navArgument("clientId") { type = NavType.StringType })
            ) { backStackEntry ->
                val clientId = backStackEntry.arguments?.getString("clientId") ?: ""
                val viewModel = EditClientViewModel(localData)
                viewModel.loadClient(clientId)

                CallScaffold(navController = navController, localData = localData).CreateScreen(
                    screen = Routes.ClientDetails.route,
                    additionalContent = {
                        ClientDetailsScreen(navController = navController, viewModel = viewModel)
                    }
                )
            }
        }
    }
}