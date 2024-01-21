package cl.lorimaralarcon.evaluacion_u3_p2.ui.composable

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.lorimaralarcon.evaluacion_u3_p2.R
import cl.lorimaralarcon.evaluacion_u3_p2.data.Solicitud
import cl.lorimaralarcon.evaluacion_u3_p2.ui.ListaSolicitudesViewModel
import java.time.LocalDate

@Composable
fun PantallaFormSolicitud(
    vmListaSolicitudes: ListaSolicitudesViewModel = viewModel(factory = ListaSolicitudesViewModel.Factory)
) {
    val contexto = LocalContext.current
    var mensajeUbicacion by rememberSaveable { mutableStateOf("Ubicación no obtenida") }

    var nombreCompleto by rememberSaveable { mutableStateOf("") }
    var rut by rememberSaveable { mutableStateOf("") }
    var fechaNacimiento by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }



    val lanzadorPermisosUbicacion = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {
            if (
                it.getOrDefault(android.Manifest.permission.ACCESS_FINE_LOCATION, false)
                ||
                it.getOrDefault(android.Manifest.permission.ACCESS_COARSE_LOCATION, false)
            ) {
                //permisos dados, recuperamos ubicacion
                vmListaSolicitudes.refrescarUbicacion()
                //.v("Funcionando Permisos", "Todo bien")
            } else {
                //mensaje de por que debe conceder los permisos
                //Log.v("No se concedieron Permisos", "Pedir nuevamente")
                mensajeUbicacion = "La obtención de tu ubicación actual nos ayuda a mejorar la seguridad y eficiencia del proceso de solicitud. " +
                        "Garantizamos que tu ubicación se utilizará exclusivamente con fines de verificación y no se compartirá con terceros sin tu consentimiento."
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Solicitud de Cuenta",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = nombreCompleto,
            onValueChange = { nombreCompleto = it },
            label = { Text("Nombre Completo") }
        )
        TextField(
            value = rut,
            onValueChange = { rut = it},
            label = { Text("RUT") }
        )
        TextField(
            value = fechaNacimiento,
            onValueChange = { fechaNacimiento = it },
            placeholder = { Text("1900-01-01")},
            label = { Text("Fecha de Nacimiento") }
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        TextField(
            value = telefono,
            onValueChange = { telefono = it},
            label = { Text("Teléfono") }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            //Log.v("Error evento click", "Error ubicacion")
            lanzadorPermisosUbicacion.launch(
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                )
            )
        }) {
            Text("Ubicación")
        }
        vmListaSolicitudes.ubicacion?.let {
            Text("Lat: ${it.latitude} | Long: ${it.longitude}")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.photo_camera),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(MaterialTheme.shapes.small)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cédula Frontal")

            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(onClick = {
        }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.photo_camera),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(MaterialTheme.shapes.small)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cédula Trasera")
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {

            vmListaSolicitudes.insertarSolicitud(
                Solicitud(
                    null,
                    nombreCompleto,
                    rut,
                    LocalDate.parse(fechaNacimiento),
                    email,
                    telefono,
                    vmListaSolicitudes.ubicacion?.latitude ?: 0.0,
                    vmListaSolicitudes.ubicacion?.longitude ?: 0.0,
                    "",
                    "",
                    LocalDate.now()
                )
            )
        }) {
            Text("SOLICITAR") }
    }
}