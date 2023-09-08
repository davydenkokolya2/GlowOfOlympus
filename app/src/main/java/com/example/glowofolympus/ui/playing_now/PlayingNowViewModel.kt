package com.example.glowofolympus.ui.playing_now

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayingNowViewModel : ViewModel() {
    private val _statePlayingNow = MutableStateFlow<Int>(0)
    val statePlayingNow: StateFlow<Int> = _statePlayingNow
    fun loadState(playingNow: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _statePlayingNow.emit(playingNow)
        }
    }

    private val _stateMusic = MutableStateFlow(0)
    val stateMusic: StateFlow<Int> = _statePlayingNow

    fun loadStateMusic(music: Int) {
        viewModelScope.launch(Dispatchers.IO){
            _stateMusic.emit(music)
        }
    }
}