package com.example.glowofolympus.ui.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.glowofolympus.databinding.FragmentLoadingBinding
import com.example.glowofolympus.ui.NavigationViewModel
import com.example.glowofolympus.util.Navigation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingFragment : Fragment() {

    private lateinit var binding: FragmentLoadingBinding
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoadingBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            delay(3000)
            navigationViewModel.loadState(Navigation.HELLO_ZEUS)
        }
        return binding.root
    }

}