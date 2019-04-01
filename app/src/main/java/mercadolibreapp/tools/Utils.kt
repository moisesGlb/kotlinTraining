package mercadolibreapp.tools

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import com.moises.mercadolibreapp.R
import com.squareup.picasso.Picasso
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

class Utils{

    fun evaluateFailure(context: Context, t: Throwable): String {
        return when (t) {
            is UnknownHostException -> context.getString(R.string.connection_message)
            is SocketTimeoutException -> context.getString(R.string.time_out_message)
            is SSLHandshakeException -> context.getString(R.string.connection_lost_message)
            else -> context.getString(R.string.default_error_message)
        }
    }

}