package com.rus.kontur.data.source.remote

import retrofit2.http.GET

interface RusKonturService {

    @GET("this_is_static/content.json")
    suspend fun getAllAudio(): Media
}