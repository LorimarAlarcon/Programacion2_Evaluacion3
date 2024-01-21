package cl.lorimaralarcon.evaluacion_u3_p2.ui

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import cl.lorimaralarcon.evaluacion_u3_p2.Aplicacion
import cl.lorimaralarcon.evaluacion_u3_p2.data.Solicitud
import cl.lorimaralarcon.evaluacion_u3_p2.data.SolicitudDao
import cl.lorimaralarcon.evaluacion_u3_p2.data.UbicacionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListaSolicitudesViewModel(
    private val solicitudDao: SolicitudDao,
    private val ubicacionRepository: UbicacionRepository
): ViewModel() {

    var solicitudes by mutableStateOf(listOf<Solicitud>())
    var ubicacion by mutableStateOf<Location?>(null)

    fun refrescarUbicacion() {
        viewModelScope.launch(Dispatchers.IO) {
            ubicacion = ubicacionRepository.getUbicacionFromPlayServices()
        }
    }

    fun insertarSolicitud(solicitud: Solicitud) {
        viewModelScope.launch(Dispatchers.IO) {
            solicitudDao.insertar(solicitud)
            obtenerSolicitudes()
        }
    }

    fun obtenerSolicitudes(): List<Solicitud> {
        viewModelScope.launch(Dispatchers.IO) {
            solicitudes = solicitudDao.obtenerTodos()
        }
        return solicitudes
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val aplicacion = (this[APPLICATION_KEY] as Aplicacion)
                ListaSolicitudesViewModel(aplicacion.solicitudDao, aplicacion.ubicacionRepository)
            }
        }
    }
}
