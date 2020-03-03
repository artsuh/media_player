package com.rus.kontur.data.source.remote

import androidx.lifecycle.LiveData
import com.rus.kontur.data.Audio
import com.rus.kontur.data.Result
import com.rus.kontur.data.source.AudioDataSource

class AudioRemoteDataSource : AudioDataSource {
    override fun observeAllAudio(): LiveData<Result<List<Audio>>> {

    }

    override suspend fun getAllAudio(): Result<List<Audio>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun refreshAllAudio() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun observeAudio(audioId: Int): LiveData<Result<Audio>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAudio(audioId: Int): Result<Audio> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun refreshAudio(audioId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteAllAudio() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteAudio(audioId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveAudio(audio: Audio) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}