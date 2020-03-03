package com.rus.kontur.data.source.remote

import retrofit2.http.GET

interface RusKonturService {

    @GET("api/v1/media?type=audio")
    suspend fun getAllAudio(): Media
}