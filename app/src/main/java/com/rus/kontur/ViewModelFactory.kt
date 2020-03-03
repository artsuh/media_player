package com.rus.kontur

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rus.kontur.audio.AudioViewModel
import com.rus.kontur.data.source.AudioRepository

class ViewModelFactory constructor(private val audioRepository: AudioRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(AudioViewModel::class.java) ->
                    AudioViewModel(audioRepository)
                else ->
                    throw IllegalAccessException("Unknown viewmodel class: ${modelClass.name}")
            }
        } as T
    }
}