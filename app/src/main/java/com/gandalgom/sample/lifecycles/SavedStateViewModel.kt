package com.gandalgom.sample.lifecycles

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SavedStateViewModel(private val state: SavedStateHandle) : ViewModel() {

    companion object {
        private const val NAME_KEY = "name"
    }

    val name: LiveData<String> = state.getLiveData(NAME_KEY)

    fun saveNewName(newName: String) {
        state[NAME_KEY] = newName
    }
}