package com.oguzhanturkmen.pixselectcasestudy.ui.welcome

import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.oguzhanturkmen.pixselectcasestudy.R
import com.oguzhanturkmen.pixselectcasestudy.data.repo.PixRepo
import com.oguzhanturkmen.pixselectcasestudy.util.changeDirection
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val pixRepo: PixRepo) : ViewModel() {
    private val _loadingProgress = MutableLiveData<Int>()
    val loadingProgress: LiveData<Int> = _loadingProgress
    private lateinit var timer: CountDownTimer

    fun startOpening(view: View) {
        timer = object : CountDownTimer(5000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                // At each tick, the progress is calculated and the progress bar is updated.
                val progress = ((5000 - millisUntilFinished) / 50).toInt()
                _loadingProgress.value = progress
            }

            override fun onFinish() {
                Navigation.changeDirection(view, WelcomeFragmentDirections.actionWelcomeFragmentToBreedFragment())
            }
        }
        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}
