package com.gandalgom.sample.lifecycles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import androidx.lifecycle.ViewModelProvider

class ChronometerActivity : AppCompatActivity() {

    private val viewModel: ChronometerViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[ChronometerViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chronometer)

        val chronometer = findViewById<Chronometer>(R.id.chronometer)

        if (viewModel.startTime == 0L) {
            val startTime = SystemClock.elapsedRealtime()
            viewModel.startTime = startTime
            chronometer.base = startTime
        } else {
            chronometer.base = viewModel.startTime
        }

        chronometer.start()
    }
}