package com.example.glowofolympus.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.glowofolympus.R
import com.example.glowofolympus.ui.hello.HelloFragment
import com.example.glowofolympus.ui.hello_zeus.HelloZeusFragment
import com.example.glowofolympus.ui.leaving.LeavingFragment
import com.example.glowofolympus.ui.loading.LoadingFragment
import com.example.glowofolympus.ui.meditation.MeditationFragment
import com.example.glowofolympus.ui.meditation_timer.MeditationTimerFragment
import com.example.glowofolympus.ui.playing_now.PlayingNowFragment
import com.example.glowofolympus.ui.win.WinFragment
import com.example.glowofolympus.util.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navigationViewModel: NavigationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            navigationViewModel.stateNavigation.collect {
                when(it) {
                    Navigation.HELLO -> replaceFragment(HelloFragment())
                    Navigation.MEDITATION -> replaceFragment(MeditationFragment())
                    Navigation.HELLO_ZEUS -> replaceFragment(HelloZeusFragment())
                    Navigation.LOADING -> replaceFragment(LoadingFragment())
                    Navigation.LEAVING -> replaceFragment(LeavingFragment())
                    Navigation.PLAYING_NOW -> replaceFragment(PlayingNowFragment())
                    Navigation.WIN -> replaceFragment(WinFragment())
                    Navigation.MEDITATION_TIMER -> replaceFragment(MeditationTimerFragment())
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }
}

