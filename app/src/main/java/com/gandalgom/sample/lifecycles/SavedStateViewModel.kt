package com.gandalgom.sample.lifecycles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SavedStateViewModel : ViewModel() {

    // TODO: Create constructor and use the LiveData from SavedStateHandle.

    private val _name: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String> get() = _name

    fun saveNewName(newName: String) {
        _name.value = newName
    }
}