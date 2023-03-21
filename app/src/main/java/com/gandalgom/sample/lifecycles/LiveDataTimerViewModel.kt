package com.gandalgom.sample.lifecycles

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

class LiveDataTimerViewModel : ViewModel() {

    companion object {
        private const val ONE_SECOND = 1000L
    }

    private val _elapsedTime: MutableLiveData<Long> = MutableLiveData()
    val elapsedTime: LiveData<Long> get() = _elapsedTime

    private val initialTime = SystemClock.elapsedRealtime()
    private val timer = Timer()

    init {
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - initialTime) / ONE_SECOND
                // setValue() cannot be called from a background thread so post to main thread.
                _elapsedTime.postValue(newValue)
            }
        }, ONE_SECOND, ONE_SECOND)
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}
