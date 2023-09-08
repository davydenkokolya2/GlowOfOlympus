package com.example.glowofolympus.ui.playing_now

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.glowofolympus.R
import com.example.glowofolympus.databinding.FragmentPlayingNowBinding
import com.example.glowofolympus.ui.leaving.LeavingFragment
import com.example.glowofolympus.ui.meditation_timer.MeditationTimerViewModel
import com.example.glowofolympus.ui.win.WinFragment
import com.example.glowofolympus.util.Constant
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlayingNowFragment : Fragment() {

    private lateinit var binding: FragmentPlayingNowBinding
    private val playingNowViewModel: PlayingNowViewModel by activityViewModels()
    private val meditationTimerViewModel: MeditationTimerViewModel by activityViewModels()
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayingNowBinding.inflate(inflater, container, false)
        binding.btnBackPlayingNow.setOnClickListener {
            showPopUpLeaving()
        }
        mediaPlayer = MediaPlayer.create(
            requireActivity(),
            Constant.listMusic[playingNowViewModel.stateMusic.value % 3]
        )
        mediaPlayer.isLooping = true

        val max =
            meditationTimerViewModel.stateMeditationTime.value.first * 60 + meditationTimerViewModel.stateMeditationTime.value.second
        binding.progressBar.max = max
        lifecycleScope.launch {
            for (i in 0..max) {
                var time = ""
                time += if (i / 60 < 10)
                    "0${i / 60}"
                else
                    "${i / 60}"
                time += ":"
                time += if (i % 60 < 10)
                    "0${i % 60}"
                else
                    "${i % 60}"

                binding.tvCurrentTime.text = time
                binding.progressBar.progress = i
                delay(1000)
            }
            mediaPlayer.stop()
            showPopUpWin()
        }
        lifecycleScope.launch {
            playingNowViewModel.stateMusic.collect {

                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(requireActivity(), Constant.listMusic[it % 3])
                mediaPlayer.start()
            }
        }

        binding.btnPause.setOnClickListener {
            if (mediaPlayer.isPlaying)
                mediaPlayer.stop()
            else
                mediaPlayer.start()
        }
        lifecycleScope.launch {
            playingNowViewModel.statePlayingNow.collect {
                when (it) {
                    0 -> {
                        binding.ivLeftCard.visibility = View.INVISIBLE
                        binding.ivMainCard.setImageResource(Constant.listOfCards[it].image)
                        binding.ivRightCard.setImageResource(Constant.listOfCards[it + 1].image)
                    }

                    14 -> {
                        binding.ivRightCard.visibility = View.INVISIBLE
                        binding.ivMainCard.setImageResource(Constant.listOfCards[it].image)
                        binding.ivLeftCard.setImageResource(Constant.listOfCards[it - 1].image)
                    }

                    else -> {
                        binding.ivRightCard.visibility = View.VISIBLE
                        binding.ivLeftCard.visibility = View.VISIBLE
                        binding.ivRightCard.setImageResource(Constant.listOfCards[it + 1].image)
                        binding.ivMainCard.setImageResource(Constant.listOfCards[it].image)
                        binding.ivLeftCard.setImageResource(Constant.listOfCards[it - 1].image)
                    }
                }
                if (Constant.listFavorites[it] == 0)
                    binding.btnHeartPlayingNow.setImageResource(R.drawable.icon_heart_false)
                else
                    binding.btnHeartPlayingNow.setImageResource(R.drawable.icon_heart_true)
                binding.tvTitle.setText(Constant.listOfCards[it].title)
                binding.tvDescription.setText(Constant.listOfCards[it].description)
            }
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

                binding.tvAllTime.text = time
                binding.tvTitleTime.text = time
            }
        }
        binding.btnNextTrack.setOnClickListener {
            if (playingNowViewModel.statePlayingNow.value < 14) {
                playingNowViewModel.loadState(playingNowViewModel.statePlayingNow.value + 1)
                playingNowViewModel.loadStateMusic(playingNowViewModel.stateMusic.value + 1)
            }
        }
        binding.btnPreviousTrack.setOnClickListener {
            if (playingNowViewModel.statePlayingNow.value > 0) {
                playingNowViewModel.loadState(playingNowViewModel.statePlayingNow.value - 1)
                playingNowViewModel.loadStateMusic(playingNowViewModel.stateMusic.value - 1)
            }
        }
        binding.btnHeartPlayingNow.setOnClickListener {
            if (Constant.listFavorites[playingNowViewModel.statePlayingNow.value] == 0) {
                Constant.listFavorites[playingNowViewModel.statePlayingNow.value] = 1
                Constant.listOfCards[playingNowViewModel.statePlayingNow.value].heart =
                    R.drawable.icon_heart_true
                binding.btnHeartPlayingNow.setImageResource(R.drawable.icon_heart_true)
            } else {
                Constant.listFavorites[playingNowViewModel.statePlayingNow.value] = 0
                Constant.listOfCards[playingNowViewModel.statePlayingNow.value].heart =
                    R.drawable.icon_heart_false
                binding.btnHeartPlayingNow.setImageResource(R.drawable.icon_heart_false)
            }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer.start()
    }

    private fun showPopUpLeaving() {
        val dialog = LeavingFragment().show(
            (activity as AppCompatActivity).supportFragmentManager,
            "showPopUp"
        )
        mediaPlayer.stop()
    }

    private fun showPopUpWin() {
        val dialog = WinFragment().show(
            (activity as AppCompatActivity).supportFragmentManager,
            "showPopUp"
        )
        mediaPlayer.stop()
    }
}