package com.example.glowofolympus.ui.hello

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.glowofolympus.databinding.FragmentHelloBinding
import com.example.glowofolympus.ui.NavigationViewModel
import com.example.glowofolympus.util.Navigation

class HelloFragment : Fragment() {

    private lateinit var binding: FragmentHelloBinding
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHelloBinding.inflate(inflater, container, false)
        binding.btnPress.setOnClickListener {
            navigationViewModel.loadState(Navigation.MEDITATION)
        }
        return binding.root
    }
}