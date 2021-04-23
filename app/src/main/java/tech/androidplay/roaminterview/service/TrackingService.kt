package tech.androidplay.roaminterview.service

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Looper
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import tech.androidplay.roaminterview.utils.PermissionUtil

/**Created by
Author: Ankush Bose
Date: 23,April,2021
 **/

@SuppressLint("VisibleForTests")
class TrackingService : LifecycleService() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate() {
        super.onCreate()
        fusedLocationProviderClient = FusedLocationProviderClient(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_START_SERVICE -> startTracking()
                ACTION_STOP_SERVICE -> stopTracking()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    @SuppressLint("MissingPermission")
    private fun startTracking() {
        isTracking.postValue(true)
        val request = LocationRequest.create().apply {
            interval = LOCATION_UPDATE_INTERVAL
            fastestInterval = FASTEST_LOCATION_INTERVAL
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        if (PermissionUtil.hasLocationPermission(this)) {
            fusedLocationProviderClient.requestLocationUpdates(
                request,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }


    private fun stopTracking() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        isTracking.postValue(false)
    }


    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            super.onLocationResult(result!!)
            result.locations.let { locations ->
                for (location in locations) {
                    latLang.postValue(
                        "${location.latitude}, ${location.longitude} " +
                                "at accuracy: ${location.accuracy}"
                    )
                }
            }
        }
    }

    companion object {
        // action constants
        const val ACTION_START_SERVICE = "ACTION_START_SERVICE"
        const val ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE"

        // Location update duration
        const val LOCATION_UPDATE_INTERVAL = 5000L
        const val FASTEST_LOCATION_INTERVAL = 5000L

        // To observe tracking is running or not
        val isTracking = MutableLiveData<Boolean>()

        // For outputs
        val latLang = MutableLiveData<String>()
    }
}