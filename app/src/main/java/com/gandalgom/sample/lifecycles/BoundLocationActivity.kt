package com.gandalgom.sample.lifecycles

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat

class BoundLocationActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_LOCATION_PERMISSION_CODE = 1
    }

    private val gpsListener = MyLocationListener(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_location)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_LOCATION_PERMISSION_CODE,
            )
        } else {
            bindLocationListener()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.size > 1
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED
        ) {
            bindLocationListener()
        } else {
            Toast.makeText(
                this,
                "This sample requires Location access",
                Toast.LENGTH_SHORT,
            ).show()
        }
    }

    private fun bindLocationListener() {
        BoundLocationManager.bindLocationListenerIn(this, gpsListener, applicationContext)
    }

    private class MyLocationListener(private val activity: BoundLocationActivity) :
        LocationListener {
        override fun onLocationChanged(location: Location) {
            val locationText = activity.findViewById<TextView>(R.id.location)
            locationText.text = String.format("${location.latitude}, ${location.longitude}")
        }

        override fun onProviderEnabled(provider: String) {
            Toast.makeText(activity, "Provider enabled: $provider", Toast.LENGTH_SHORT).show()
        }
    }
}
