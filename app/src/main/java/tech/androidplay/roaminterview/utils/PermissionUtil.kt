package tech.androidplay.roaminterview.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.fragment.app.Fragment
import pub.devrel.easypermissions.EasyPermissions

object PermissionUtil {

    fun hasLocationPermission(context: Context) =
        if (Build.VERSION.SDK_INT < ANDROID_10) {
            EasyPermissions.hasPermissions(
                context,
                FINE_LOCATION,
                COARSE_LOCATION
            )
        } else {
            EasyPermissions.hasPermissions(
                context,
                FINE_LOCATION,
                COARSE_LOCATION,
                BACKGROUND_LOCATION
            )
        }

    fun askPermissions(context: Activity) {
        if (hasLocationPermission(context)) {
            return
        } else {
            if (Build.VERSION.SDK_INT < ANDROID_10) {
                EasyPermissions.requestPermissions(
                    context,
                    PERMISSION_REQUEST_RATIONAL,
                    PERMISSION_REQUEST_CODE,
                    FINE_LOCATION,
                    COARSE_LOCATION
                )
            } else {
                EasyPermissions.requestPermissions(
                    context,
                    PERMISSION_REQUEST_RATIONAL,
                    PERMISSION_REQUEST_CODE,
                    FINE_LOCATION,
                    COARSE_LOCATION,
                    BACKGROUND_LOCATION
                )
            }
        }
    }
}