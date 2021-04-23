package tech.androidplay.roaminterview.utils

import android.Manifest
import android.os.Build

/**Created by
Author: Ankush Bose
Date: 23,April,2021
 **/

/*const val NOTIFICATION_CHANNEL_ID = "tracking_channel"
const val NOTIFICATION_CHANNEL_NAME = "Tracking"
const val NOTIFICATION_ID = 1*/

// Permision constants
const val PERMISSION_REQUEST_RATIONAL =
    "You need to accept location permissions to use this app"
const val PERMISSION_REQUEST_CODE = 100

const val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
const val COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
const val BACKGROUND_LOCATION = Manifest.permission.ACCESS_BACKGROUND_LOCATION

// SDK constants
const val ANDROID_OREO = Build.VERSION_CODES.O
const val ANDROID_10 = Build.VERSION_CODES.Q