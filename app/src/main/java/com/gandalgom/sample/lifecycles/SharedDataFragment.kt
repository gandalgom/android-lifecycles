package com.gandalgom.sample.lifecycles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.activityViewModels

class SharedDataFragment : Fragment() {

    private lateinit var seekBar: SeekBar

    private val sharedDataViewModel: SharedDataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_shared_data, container, false)
        seekBar = root.findViewById(R.id.seekBar)

        subscribeSeekBar()

        return root
    }

    private fun subscribeSeekBar() {
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    sharedDataViewModel.seekBarValue.value = progress
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        sharedDataViewModel.seekBarValue.observe(requireActivity()) { value ->
            seekBar.progress = value
        }
    }
}