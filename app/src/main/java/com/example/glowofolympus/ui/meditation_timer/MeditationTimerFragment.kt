package com.example.glowofolympus.ui.meditation_timer

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.glowofolympus.databinding.FragmentMeditationTimerBinding
import kotlinx.coroutines.launch

class MeditationTimerFragment : DialogFragment() {

    private lateinit var binding: FragmentMeditationTimerBinding
    private val meditationTimerViewModel: MeditationTimerViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return dialog;
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeditationTimerBinding.inflate(inflater, container, false)
        binding.btnExitTimer.setOnClickListener {
            onStop()
            meditationTimerViewModel.loadState(0 to 0)
        }
        lifecycleScope.launch {
            meditationTimerViewModel.stateMeditationTime.collect {
                var time = ""
                time += if (it.first < 10)
                    "0${it.first}"
                else
                    "${it.first}"
                time += ":"
                time += if (it.second < 10)
                    "0${it.second}"
                else
                    "${it.second}"

                binding.tvTime.text = time
            }
        }
        binding.btnSave.setOnClickListener {
            onStop()
        }
        binding.numberPicker.minValue = 0
        binding.numberPicker.maxValue = 59
        binding.numberPicker2.minValue = 0
        binding.numberPicker2.maxValue = 59

        binding.numberPicker.setOnValueChangedListener { numberPicker, i, i2 ->
            meditationTimerViewModel.loadState(i2 to meditationTimerViewModel.stateMeditationTime.value.second)
        }
        binding.numberPicker2.setOnValueChangedListener { timePicker, i, i2 ->
            meditationTimerViewModel.loadState(meditationTimerViewModel.stateMeditationTime.value.first to i2)
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setLayout(width, height)
        }
    }
}