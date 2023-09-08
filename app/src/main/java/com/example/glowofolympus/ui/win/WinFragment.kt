package com.example.glowofolympus.ui.win

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
import com.example.glowofolympus.databinding.FragmentWinBinding
import com.example.glowofolympus.ui.NavigationViewModel
import com.example.glowofolympus.util.Navigation

class WinFragment : DialogFragment() {

    private lateinit var binding: FragmentWinBinding
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return dialog
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWinBinding.inflate(inflater, container, false)
        binding.btnExitWin.setOnClickListener {
            onStop()
            navigationViewModel.loadState(Navigation.MEDITATION)
        }
        binding.btnGoToMenu.setOnClickListener {
            onStop()
            navigationViewModel.loadState(Navigation.MEDITATION)
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