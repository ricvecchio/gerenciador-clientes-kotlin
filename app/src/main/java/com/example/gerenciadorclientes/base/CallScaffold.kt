import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gerenciadorclientes.base.Routes
import com.example.gerenciadorclientes.model.SharedPreferences
import com.example.gerenciadorclientes.screens.CreateClientScreen
import com.example.gerenciadorclientes.screens.CreateClientViewModel
import com.example.gerenciadorclientes.screens.EditClientScreen
import com.example.gerenciadorclientes.screens.EditClientViewModel
import com.example.gerenciadorclientes.screens.ListClientScreen
import com.example.gerenciadorclientes.screens.ListClientViewModel

class CallScaffold(val navController: NavController, val localData: SharedPreferences) {
    val createClientViewModel = CreateClientViewModel(localData)
    val editClientViewModel = EditClientViewModel(localData)
    val listClientViewModel = ListClientViewModel(localData)

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CreateScreen(screen: String, additionalContent: @Composable (() -> Unit)? = null) {
        Scaffold(
            topBar = {
                when (screen) {
                    Routes.ClientList.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Lista de Clientes") },
                        )
                    }
                    Routes.ClientCreate.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Cadastrar Cliente") },
                            navigationIcon = {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                                }
                            }
                        )
                    }
                    Routes.ClientEdit.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Editar Cliente") },
                            navigationIcon = {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                                }
                            }
                        )
                    }
                    Routes.ClientDetails.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Detalhes do Cliente") },
                            navigationIcon = {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                                }
                            }
                        )
                    }
                }
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                when (screen) {
                    Routes.ClientList.route -> ListClientScreen(padding, navController, listClientViewModel)
                    Routes.ClientCreate.route -> CreateClientScreen(padding, navController, createClientViewModel)
                    Routes.ClientEdit.route -> EditClientScreen(padding, navController, editClientViewModel)
                }

                additionalContent?.invoke()
            }
        }
    }
}