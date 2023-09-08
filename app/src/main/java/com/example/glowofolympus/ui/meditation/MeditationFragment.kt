package com.example.glowofolympus.ui.meditation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.glowofolympus.R
import com.example.glowofolympus.databinding.FragmentMeditationBinding
import com.example.glowofolympus.domain.CardModel
import com.example.glowofolympus.ui.CardViewAdapter
import com.example.glowofolympus.ui.NavigationViewModel
import com.example.glowofolympus.ui.meditation_timer.MeditationTimerFragment
import com.example.glowofolympus.ui.meditation_timer.MeditationTimerViewModel
import com.example.glowofolympus.ui.playing_now.PlayingNowViewModel
import com.example.glowofolympus.util.Constant
import com.example.glowofolympus.util.Meditation
import com.example.glowofolympus.util.Navigation
import kotlinx.coroutines.launch

class MeditationFragment : Fragment() {

    private lateinit var binding: FragmentMeditationBinding
    private val navigationViewModel: NavigationViewModel by activityViewModels()
    private val meditationViewModel: MeditationViewModel by activityViewModels()
    private val playingNowViewModel: PlayingNowViewModel by activityViewModels()
    private val meditationTimerViewModel: MeditationTimerViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeditationBinding.inflate(inflater, container, false)
        binding.btnChangeTime.setOnClickListener {
            showPopUpTimer()
        }
        binding.btnBackMeditation.setOnClickListener {
            navigationViewModel.loadState(Navigation.HELLO)
        }

        val layoutManager =
            GridLayoutManager(requireActivity(), 2)
        binding.rvCards.layoutManager = layoutManager
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

                binding.tvTimeMediation.text = time
            }
        }
        lifecycleScope.launch {
            meditationViewModel.stateMeditation.collect {
                when (it) {
                    Meditation.FAVORITE -> {
                        binding.btnAll.setImageResource(R.drawable.icon_white_false_button)
                        binding.btnFavorite.setImageResource(R.drawable.icon_white_true_button)
                        val list = mutableListOf<CardModel>()
                        for (i in 0..14)
                            if (Constant.listFavorites[i] == 1)
                                list.add(Constant.listOfCards[i])
                        binding.rvCards.adapter = CardViewAdapter(list, ::onClick)
                    }

                    Meditation.ALL -> {
                        binding.btnAll.setImageResource(R.drawable.icon_white_true_button)
                        binding.btnFavorite.setImageResource(R.drawable.icon_white_false_button)
                        binding.rvCards.adapter = CardViewAdapter(Constant.listOfCards, ::onClick)
                    }
                }
            }
        }





        binding.btnAll.setOnClickListener {
            meditationViewModel.loadState(Meditation.ALL)
        }
        binding.btnFavorite.setOnClickListener {
            meditationViewModel.loadState(Meditation.FAVORITE)
        }
        return binding.root
    }

    private fun showPopUpTimer() {
        val dialog = MeditationTimerFragment().show(
            (activity as AppCompatActivity).supportFragmentManager,
            "showPopUp"
        )
    }

    private fun onClick(position: Int) {
        playingNowViewModel.loadState(position)
        navigationViewModel.loadState(Navigation.PLAYING_NOW)
    }

}