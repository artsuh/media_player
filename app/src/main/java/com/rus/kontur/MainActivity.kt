package com.rus.kontur

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.rus.kontur.data.Audio
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
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(
            object : TypeToken<List<Audio>>() {}.type,
            CustomConverter()
        )
        val gson = gsonBuilder.create()


        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(loggingInterceptor)
        val retrofit = Retrofit.Builder().baseUrl("http://dev.ruskontur.com/")
            .addConverterFactory(GsonConverterFactory.create(gson)).client(builder.build()).build()

        val rusKonturService = retrofit.create(RusKonturService::class.java)

        runBlocking {
            withContext(Dispatchers.IO) {
                val media = rusKonturService.getAllAudio()
                Log.d(MainApplication.TAG, "fetched remote audio size: ${media.media.size}")
            }
        }

        val database =
            Room.databaseBuilder(applicationContext, MediaDatabase::class.java, "media-database")
                .build()


        val audioRemoteDataSource = AudioRemoteDataSource(rusKonturService)
        val audioLocalDataSource = AudioLocalDataSource(database.audioDao())

    }

    @Suppress("CAST_NEVER_SUCCEEDS")
    class CustomConverter : JsonDeserializer<List<Audio>> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): List<Audio> {
            var result: List<Audio> = emptyList()
            val jsonArray = json!!.asJsonObject.get("media").asJsonArray
            jsonArray.iterator().forEach {
                val asJsonObject = it.asJsonObject
                result += Audio(
                    asJsonObject.get("id") as Int,
                    asJsonObject.get("type") as String,
                    asJsonObject.get("author") as String,
                    asJsonObject.get("title") as String,
                    asJsonObject.get("url").asString
                )
            }
            return result
        }

    }
}
