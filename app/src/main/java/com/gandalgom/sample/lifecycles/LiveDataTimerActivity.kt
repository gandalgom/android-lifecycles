package com.gandalgom.sample.lifecycles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class LiveDataTimerActivity : AppCompatActivity() {

    private val liveDataTimerViewModel: LiveDataTimerViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[LiveDataTimerViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data_timer)

        subscribe()
    }

    private fun subscribe() {
        val elapsedTimeObserver: Observer<Long> = Observer<Long> { time ->
            val newText = this.resources.getString(R.string.seconds, time)
            findViewById<TextView>(R.id.timer_textview).text = newText
            Log.d("LiveDataTimerActivity", "Updating timer")
        }

        // TODO: observe the ViewModel's elapsed time
    }
}