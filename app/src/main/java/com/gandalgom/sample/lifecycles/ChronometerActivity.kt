package com.gandalgom.sample.lifecycles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Chronometer

class ChronometerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chronometer)

        val chronometer = findViewById<Chronometer>(R.id.chronometer)

        chronometer.start()
    }
}