package com.gandalgom.sample.lifecycles

import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

// LifecycleEvent annotation is deprecated.
// Change LifecycleObserver to DefaultLifecycleObserver and implements onResume(LifecycleOwner)
// and onPause(LifecycleOwner).

class BoundLocationManager(
    lifecycleOwner: LifecycleOwner,
    private val locationListener: LocationListener,
    private val context: Context,
) : DefaultLifecycleObserver {

    companion object {
        fun bindLocationListenerIn(
            lifecycleOwner: LifecycleOwner,
            locationListener: LocationListener,
            context: Context
        ) {
            BoundLocationManager(lifecycleOwner, locationListener, context)
        }
    }

    private var locationManager: LocationManager? = null

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        addLocationListener()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        removeLocationListener()
    }

    @SuppressWarnings("MissingPermission")
    private fun addLocationListener() {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager?.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0,
            0.toFloat(),
            locationListener,
        )
        if (locationManager != null) {
            Log.d("BoundLocationManager", "Listener added")
        }

        // Force an update with the last location, if available.
        // When GPS location value is null, get location value from network provider.
        val lastLocation = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            ?: locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        if (lastLocation != null) {
            locationListener.onLocationChanged(lastLocation)
        }
    }

    private fun removeLocationListener() {
        locationManager?.removeUpdates(locationListener)
        locationManager = null
        Log.d("BoundLocationManager", "Listener removed")
    }
}