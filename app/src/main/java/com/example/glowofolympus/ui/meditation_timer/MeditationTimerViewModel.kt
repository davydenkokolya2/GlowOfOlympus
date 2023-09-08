package com.example.glowofolympus.ui.meditation_timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MeditationTimerViewModel : ViewModel() {
    private val _stateMeditationTime = MutableStateFlow<Pair<Int, Int>>(0 to 0)
    val stateMeditationTime: StateFlow<Pair<Int, Int>> = _stateMeditationTime
    fun loadState(meditationTime: Pair<Int, Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateMeditationTime.emit(meditationTime)
        }
    }
}