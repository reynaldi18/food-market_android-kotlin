package com.fMarket.app.api

import com.fMarket.app.api.request.SignInReq
import com.fMarket.app.api.response.AuthRes
import com.fMarket.app.api.response.UserRes
import com.fMarket.app.data.Init
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
    }

    @GET("init")
    suspend fun init(): Response<Init>

    @POST("oauth")
    suspend fun signIn(
        @Body body: SignInReq?,
    ): Response<AuthRes>

    @GET("api/user")
    suspend fun getProfile(): Response<UserRes>
}
