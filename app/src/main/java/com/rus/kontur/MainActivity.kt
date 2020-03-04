package com.rus.kontur

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        runBlocking {
//            withContext(Dispatchers.IO) {
//                val media = rusKonturService.getAllAudio()
//                Log.d(MainApplication.TAG, "fetched remote audio size: ${media.media.size}")
//            }
//        }


    }

}
