package com.rus.kontur.util

import androidx.fragment.app.Fragment
import com.rus.kontur.ViewModelFactory
import com.rus.kontur.data.source.MainApplication

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val audioRepository = (requireContext().applicationContext as MainApplication).audioRepository
    return ViewModelFactory(audioRepository)
}