package cl.lorimaralarcon.evaluacion_u3_p2

import android.app.Application
import androidx.room.Room
import cl.lorimaralarcon.evaluacion_u3_p2.data.BaseDatos
import cl.lorimaralarcon.evaluacion_u3_p2.data.UbicacionRepository
import com.google.android.gms.location.LocationServices

class Aplicacion : Application() {

    val db by lazy { Room.databaseBuilder(this, BaseDatos::class.java, "solicitudes.db").build() }
    val solicitudDao by lazy { db.solicitudDao() }
    val locationService by lazy { LocationServices.getFusedLocationProviderClient(this) }
    val ubicacionRepository by lazy { UbicacionRepository(locationService) }
}
