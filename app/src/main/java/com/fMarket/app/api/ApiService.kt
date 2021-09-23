package com.fMarket.app.api

import com.fMarket.app.data.Init
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
    }

    @GET("init")
    suspend fun init(): Response<Init>
}
