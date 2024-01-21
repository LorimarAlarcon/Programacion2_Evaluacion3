package cl.lorimaralarcon.evaluacion_u3_p2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Solicitud::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract  class BaseDatos : RoomDatabase() {
    abstract fun solicitudDao(): SolicitudDao
}
