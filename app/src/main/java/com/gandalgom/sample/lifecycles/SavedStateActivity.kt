package com.gandalgom.sample.lifecycles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class SavedStateActivity : AppCompatActivity() {

    private val savedStateViewModel: SavedStateViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[SavedStateViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_state)

        // Show the ViewModel property's value in a TextView
        savedStateViewModel.name.observe(this) { savedName ->
            findViewById<TextView>(R.id.result_text).text =
                String.format("${getString(R.string.saved_in_vm)} $savedName")
        }

        // Save button
        findViewById<Button>(R.id.save_button).setOnClickListener {
            val newName = findViewById<EditText>(R.id.input_name).text.toString()
            savedStateViewModel.saveNewName(newName)
        }
    }
}