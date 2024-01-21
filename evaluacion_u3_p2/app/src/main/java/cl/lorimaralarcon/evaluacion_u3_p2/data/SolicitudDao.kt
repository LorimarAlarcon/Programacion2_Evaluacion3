package cl.lorimaralarcon.evaluacion_u3_p2.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SolicitudDao {

    @Query("SELECT * FROM Solicitud ORDER BY fechaCreacionSolicitud DESC")
    suspend fun  obtenerTodos(): List<Solicitud>

    @Query("SELECT * FROM Solicitud WHERE id = :id")
    suspend fun obtenerPorId(id:Long): Solicitud

    @Insert
    suspend fun insertar(solicitud: Solicitud)

    @Update
    suspend fun modificar(solicitud: Solicitud)

    @Query("DELETE FROM Solicitud WHERE id = :id")
    suspend fun eliminar(id: Long)
}
