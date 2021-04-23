package tech.androidplay.roaminterview.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

/*fun logMessage(message: String) {
    Log.d("ANKUSH", message)
}*/

fun Context.showToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

/*// For getting 6 place decimal location accuracy
fun Double.formatDouble(): Double {
    val input = this
    val decimalFormat = DecimalFormat("##.######")
    return decimalFormat.format(input).toDouble()
}*/


