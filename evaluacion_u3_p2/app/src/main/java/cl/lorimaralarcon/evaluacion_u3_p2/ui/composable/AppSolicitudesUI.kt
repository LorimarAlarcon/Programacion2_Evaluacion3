package cl.lorimaralarcon.evaluacion_u3_p2.ui.composable

import androidx.compose.runtime.Composable
import cl.lorimaralarcon.evaluacion_u3_p2.ui.ListaSolicitudesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppSolicitudesUI(
    navController: NavHostController = rememberNavController(),
    vmListaSolicitudes: ListaSolicitudesViewModel = viewModel(factory = ListaSolicitudesViewModel.Factory)
) {
    NavHost(
        navController = navController,
        startDestination = "inicio")
    {
        composable("inicio") {
            PantallaLogin(
                navController
            )
        }
        composable("form") {
            PantallaFormSolicitud(vmListaSolicitudes = vmListaSolicitudes)
        }
    }
}