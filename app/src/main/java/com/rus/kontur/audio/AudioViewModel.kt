package com.rus.kontur.audio

import androidx.lifecycle.*
import com.rus.kontur.R
import com.rus.kontur.data.Audio
import com.rus.kontur.data.source.AudioRepository
import kotlinx.coroutines.launch

class AudioViewModel(
    private val audioRepository: AudioRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _forceUpdate = MutableLiveData<Boolean>(false)

    private val _audio: LiveData<List<Audio>> = _forceUpdate.switchMap { forceUpdate ->
        audioRepository.observeAllAudio().distinctUntilChanged().switchMap { filterAudio(it) }
    }

    val audio: LiveData<List<Audio>> = _audio

    private val isDataLoadingError = MutableLiveData<Boolean>()

//    private val _snackbarText = MutableLiveData<Event<Int>>()
//    val snackbarText: LiveData<Event<Int>> = _snackbarText

    private fun filterAudio(audioResult: com.rus.kontur.data.Result<List<Audio>>): LiveData<List<Audio>> {
        // TODO: liveData builder???
        val result = MutableLiveData<List<Audio>>()

        if (audioResult is com.rus.kontur.data.Result.Success) {
            isDataLoadingError.value = false
            viewModelScope.launch {
                result.value = audioResult.data
            }
        } else {
            result.value = emptyList()
            showSnackbarMessage(R.string.cant_load_audio)
            isDataLoadingError.value = true
        }

        return result
    }

    private fun showSnackbarMessage(message: Int) {
//        _snackbarText.value = Event(message)
    }

    fun loadAudio(forceUpdate: Boolean) {
        _forceUpdate.value = forceUpdate
    }

    fun refresh() {
        _forceUpdate.value = true
    }
}
