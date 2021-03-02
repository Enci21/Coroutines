package util

import android.content.Context
import android.widget.Toast


fun Context.toast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

