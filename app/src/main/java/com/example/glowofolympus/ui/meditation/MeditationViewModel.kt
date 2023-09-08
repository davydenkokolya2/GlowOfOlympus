package com.example.glowofolympus.ui.meditation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.glowofolympus.util.Meditation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MeditationViewModel : ViewModel() {
    private val _stateMeditation = MutableStateFlow<Meditation>(Meditation.ALL)
    val stateMeditation: StateFlow<Meditation> = _stateMeditation
    fun loadState(meditation: Meditation) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateMeditation.emit(meditation)
        }
    }
}