package cl.lorimaralarcon.evaluacion_u3_p2.ui.composable

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore

fun archivoPublicoFoto(
    contexto: Context,
    nombreArchivo: String,
    mime: String = "image/jpeg"
): Uri? = ContentValues().run {
    put(MediaStore.MediaColumns.DISPLAY_NAME, nombreArchivo)
    put(MediaStore.MediaColumns.MIME_TYPE, mime)
    contexto.contentResolver.insert(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        this
    )
}
