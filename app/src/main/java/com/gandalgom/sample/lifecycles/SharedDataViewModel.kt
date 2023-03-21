package com.gandalgom.sample.lifecycles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedDataViewModel : ViewModel() {
    val seekBarValue = MutableLiveData<Int>()
}