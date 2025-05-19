import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.heightIn
import com.example.gerenciadorclientes.R
import com.example.gerenciadorclientes.base.Routes
import com.example.gerenciadorclientes.model.SharedPreferences
import com.example.gerenciadorclientes.screens.CreateClientScreen
import com.example.gerenciadorclientes.screens.CreateClientViewModel
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
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.clientes),
                        contentDescription = "Logo Clientes",
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 120.dp)
                            .aspectRatio(16 / 9f),
                        contentScale = ContentScale.FillWidth
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    CenterAlignedTopAppBar(
                        title = {
                            when (screen) {
                                Routes.ClientList.route -> Text(text = "Lista de Clientes")
                                Routes.ClientCreate.route -> Text(text = "Cadastrar Cliente")
                                Routes.ClientEdit.route -> Text(text = "Editar Cliente")
                                Routes.ClientDetails.route -> Text(text = "Detalhes do Cliente")
                            }
                        },
                        navigationIcon = {
                            if (screen != Routes.ClientList.route) {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Voltar"
                                    )
                                }
                            }
                        },
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                when (screen) {
                    Routes.ClientList.route -> ListClientScreen(padding, navController, listClientViewModel)
                    Routes.ClientCreate.route -> CreateClientScreen(padding, navController, createClientViewModel)
                    Routes.ClientEdit.route -> {
                        additionalContent?.invoke()
                    }
                }
            }
        }
    }
}
