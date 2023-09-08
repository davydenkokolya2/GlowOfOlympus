package com.example.glowofolympus.ui.hello_zeus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.glowofolympus.databinding.FragmentHelloZeusBinding
import com.example.glowofolympus.ui.NavigationViewModel
import com.example.glowofolympus.util.Navigation

class HelloZeusFragment : Fragment() {

    private lateinit var binding: FragmentHelloZeusBinding
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHelloZeusBinding.inflate(inflater, container, false)
        binding.btnLetsRelax.setOnClickListener {
            navigationViewModel.loadState(Navigation.HELLO)
        }
        return binding.root
    }

}