package com.rus.kontur

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.rus.kontur.data.source.DefaultAudioRepository
import com.rus.kontur.data.source.MainApplication
import com.rus.kontur.data.source.local.AudioLocalDataSource
import com.rus.kontur.data.source.local.MediaDatabase
import com.rus.kontur.data.source.remote.AudioRemoteDataSource
import com.rus.kontur.data.source.remote.RusKonturService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
