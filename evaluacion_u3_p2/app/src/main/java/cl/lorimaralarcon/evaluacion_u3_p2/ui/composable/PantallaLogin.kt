package cl.lorimaralarcon.evaluacion_u3_p2.ui.composable

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cl.lorimaralarcon.evaluacion_u3_p2.R

@Composable
fun PantallaLogin(navController: NavHostController) {
    val(usuario, setUsuario) = remember { mutableStateOf("")}
    val(contrasena, setContrasena) = remember { mutableStateOf("")}



    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.peso),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clip(MaterialTheme.shapes.small)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "IplaBank",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = usuario,
            onValueChange = {setUsuario(it)},
            label = { Text("Usuario")}
        )
        TextField(
            value = contrasena,
            onValueChange = {setContrasena(it)},
            label = {Text("Contraseña")}
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
        })
        { Text("Ingresar")}
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            navController.navigate("form")
        }) { Text("Solcitar Cuenta")}
    }
}