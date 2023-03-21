package com.gandalgom.sample.lifecycles

import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

class BoundLocationManager(
    lifecycleOwner: LifecycleOwner,
    private val locationListener: LocationListener,
    private val context: Context,
) : LifecycleObserver {

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

    // TODO: Add lifecycle observer in construction

    // TODO: Call this on resume
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

    // TODO: Call this on pause
    private fun removeLocationListener() {
        locationManager?.removeUpdates(locationListener)
        locationManager = null
        Log.d("BoundLocationManager", "Listener removed")
    }
}