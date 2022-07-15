package uz.gita.memorygame.ui.viewmodel

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PlaygroundViewModel : ViewModel() {

    val timerChannel = Channel<Int>()
    val timerFlow = timerChannel.receiveAsFlow()

    val changeClickableViewLiveData = MutableLiveData<ImageView>()
    val timerViewLiveData = MutableLiveData(0)
    var counter = 0

    fun changeClickable(imageView: ImageView) {
        viewModelScope.launch(Dispatchers.Main) {
            changeClickableViewLiveData.postValue(imageView)
            delay(100)
            changeClickableViewLiveData.postValue(imageView)
        }
    }

    fun startTimer(isPlaying: Boolean) {
        viewModelScope.launch {
            counter = 0
            do {
                delay(1000L)
                timerChannel.send(counter++)
            } while (isPlaying)
        }
    }
}