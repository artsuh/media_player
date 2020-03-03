package com.rus.kontur.data.source

import androidx.lifecycle.LiveData
import com.rus.kontur.data.Audio
import com.rus.kontur.data.Result

interface AudioRepository {
    fun observeAllAudio(): LiveData<Result<List<Audio>>>

    suspend fun getAllAudio(forceUpdate: Boolean): com.rus.kontur.data.Result<List<Audio>>

    suspend fun refreshAllAudio()

    fun observeAudio(audioId: Int): LiveData<Result<Audio>>

    suspend fun getAudio(audioId: Int, forceUpdate: Boolean): com.rus.kontur.data.Result<Audio>

    suspend fun refreshAudio(audioId: Int)

    suspend fun deleteAllAudio()

    suspend fun deleteAudio(audioId: Int)
}