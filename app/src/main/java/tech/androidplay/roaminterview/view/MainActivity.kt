package tech.androidplay.roaminterview.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import tech.androidplay.roaminterview.R
import tech.androidplay.roaminterview.databinding.ActivityMainBinding
import tech.androidplay.roaminterview.service.TrackingService
import tech.androidplay.roaminterview.service.TrackingService.Companion.ACTION_START_SERVICE
import tech.androidplay.roaminterview.service.TrackingService.Companion.ACTION_STOP_SERVICE
import tech.androidplay.roaminterview.utils.PermissionUtil
import tech.androidplay.roaminterview.utils.showToast

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var isTracking = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (!PermissionUtil.hasLocationPermission(this)) PermissionUtil.askPermissions(this)

        setOnClickListeners()
        setObservers()
    }


    private fun setOnClickListeners() {
        binding.btnStartRun.setOnClickListener {
            if (isTracking)
                showToast("Already tracking")
            else {
                sendCommandToService(ACTION_START_SERVICE)
                showToast("Tracking started")
            }
        }

        binding.btnFinishRun.setOnClickListener {
            if (!isTracking)
                showToast("Tracking not started")
            else {
                sendCommandToService(ACTION_STOP_SERVICE)
                showToast("Tracking stopped")
            }
        }
    }


    private fun sendCommandToService(action: String) {
        Intent(this, TrackingService::class.java).also {
            it.action = action
            startService(it)
        }
    }


    private fun setObservers() {
        TrackingService.isTracking.observe(this, {
            this.isTracking = it
        })

        TrackingService.latLang.observe(this, {
            showToast(it)
        })
    }
}