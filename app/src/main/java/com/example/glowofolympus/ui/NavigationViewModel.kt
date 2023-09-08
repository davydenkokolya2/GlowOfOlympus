package com.example.glowofolympus.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.glowofolympus.util.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(): ViewModel() {
    private val _stateNavigation = MutableStateFlow<Navigation>(Navigation.LOADING)
    val stateNavigation: StateFlow<Navigation> = _stateNavigation
    fun loadState(navigation: Navigation) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateNavigation.emit(navigation)
        }
    }
}