package com.rus.kontur.data.source

import android.app.Application
import androidx.room.Room
import com.rus.kontur.data.source.local.AudioLocalDataSource
import com.rus.kontur.data.source.local.MediaDatabase
import com.rus.kontur.data.source.remote.AudioRemoteDataSource
import com.rus.kontur.data.source.remote.RusKonturService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainApplication : Application() {
    lateinit var audioRepository: AudioRepository

    override fun onCreate() {
        super.onCreate()
        val database =
            Room.databaseBuilder(applicationContext, MediaDatabase::class.java, "media-database")
                .build()


        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(loggingInterceptor)
        val retrofit = Retrofit.Builder().baseUrl("http://dev.ruskontur.com/")
            .addConverterFactory(GsonConverterFactory.create(/*gson*/)).client(builder.build())
            .build()

        val rusKonturService = retrofit.create(RusKonturService::class.java)

        val audioRemoteDataSource = AudioRemoteDataSource(rusKonturService)
        val audioLocalDataSource = AudioLocalDataSource(database.audioDao())

        audioRepository = DefaultAudioRepository(audioLocalDataSource, audioRemoteDataSource)
    }

    companion object {
        const val TAG = "RusKontur"
    }
}