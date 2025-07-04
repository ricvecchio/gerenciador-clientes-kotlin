import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
                TopAppBar(
                    title = {
                        Text(
                            text = when (screen) {
                                Routes.ClientList.route -> "Lista de Clientes"
                                Routes.ClientCreate.route -> "Cadastrar Cliente"
                                Routes.ClientEdit.route -> "Editar Cliente"
                                Routes.ClientDetails.route -> "Detalhes do Cliente"
                                else -> ""
                            },
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    navigationIcon = if (screen != Routes.ClientList.route) {
                        {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Voltar"
                                )
                            }
                        }
                    } else {
                        {}
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.clientes),
                    contentDescription = "Logo Clientes",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 180.dp)
                        .aspectRatio(16 / 9f),
                    contentScale = ContentScale.FillWidth
                )

                Spacer(modifier = Modifier.height(8.dp))

                when (screen) {
                    Routes.ClientList.route -> ListClientScreen(
                        padding,
                        navController,
                        listClientViewModel
                    )

                    Routes.ClientCreate.route -> CreateClientScreen(
                        padding,
                        navController,
                        createClientViewModel
                    )

                    Routes.ClientEdit.route -> {
                        additionalContent?.invoke()
                    }
                }
            }
        }
    }
}
