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

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(loggingInterceptor)
        val retrofit = Retrofit.Builder().baseUrl("http://dev.ruskontur.com/")
            .addConverterFactory(GsonConverterFactory.create(/*gson*/)).client(builder.build()).build()

        val rusKonturService = retrofit.create(RusKonturService::class.java)

//        runBlocking {
//            withContext(Dispatchers.IO) {
//                val media = rusKonturService.getAllAudio()
//                Log.d(MainApplication.TAG, "fetched remote audio size: ${media.media.size}")
//            }
//        }

        val database =
            Room.databaseBuilder(applicationContext, MediaDatabase::class.java, "media-database")
                .build()


        val audioRemoteDataSource = AudioRemoteDataSource(rusKonturService)
        val audioLocalDataSource = AudioLocalDataSource(database.audioDao())

        val audioRepository = DefaultAudioRepository(audioLocalDataSource, audioRemoteDataSource)


    }

}
