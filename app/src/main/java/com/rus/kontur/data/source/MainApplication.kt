package com.rus.kontur.data.source

import android.app.Application

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        const val TAG = "RusKontur"
    }
}