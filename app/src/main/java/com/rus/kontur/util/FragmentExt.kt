package com.rus.kontur.util

import androidx.fragment.app.Fragment
import com.rus.kontur.ViewModelFactory
import com.rus.kontur.MainApplication

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val audioRepository = (context!!.applicationContext as MainApplication).audioRepository
    return ViewModelFactory(audioRepository)
}