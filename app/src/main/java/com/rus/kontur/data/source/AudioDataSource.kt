package com.rus.kontur.data.source

import android.arch.lifecycle.LiveData
import com.rus.kontur.data.Audio
import com.rus.kontur.data.Result

interface AudioDataSource {
    fun observeAllAudio(): androidx.lifecycle.LiveData<Result<List<Audio>>>

    suspend fun getAllAudio(): com.rus.kontur.data.Result<List<Audio>>

    suspend fun refreshAllAudio()

    fun observeAudio(audioId: Int): androidx.lifecycle.LiveData<Result<Audio>>

    suspend fun getAudio(audioId: Int): com.rus.kontur.data.Result<Audio>

    suspend fun refreshAudio(audioId: Int)

    suspend fun deleteAllAudio()

    suspend fun deleteAudio(audioId: Int)
}