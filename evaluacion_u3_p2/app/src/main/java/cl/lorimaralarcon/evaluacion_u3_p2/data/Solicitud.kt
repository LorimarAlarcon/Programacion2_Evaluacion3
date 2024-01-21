package cl.lorimaralarcon.evaluacion_u3_p2.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Solicitud (
    @PrimaryKey(autoGenerate = true) var id:Long? = null,
    var nombreCompleto: String,
    var rut: String,
    var fechaNacimiento: LocalDate,
    var email: String,
    var telefono: String,
    var latitud: Double,
    var longitud: Double,
    var imagenDelanteraCedula: String,
    var imagenTraseraCedula: String,
    var fechaCreacionSolicitud: LocalDate
)
