package com.movieApp.app.api

import com.movieApp.app.api.request.SignInReq
import com.movieApp.app.api.request.SignUpReq
import com.movieApp.app.api.response.AuthRes
import com.movieApp.app.api.response.CoreRes
import com.movieApp.app.api.response.ProductsRes
import com.movieApp.app.api.response.UserRes
import com.movieApp.app.data.Init
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

    @POST("api/regis")
    suspend fun signUp(
        @Body body: SignUpReq?,
    ): Response<CoreRes>

    @GET("api/user")
    suspend fun getProfile(): Response<UserRes>

    @GET("api/highlight")
    suspend fun getHighlight(): Response<ProductsRes>

    @GET("api/products/new")
    suspend fun getNewProducts(): Response<ProductsRes>

    @GET("api/products/popular")
    suspend fun getPopularProducts(): Response<ProductsRes>

    @GET("api/products/recommended")
    suspend fun getRecommendedProducts(): Response<ProductsRes>
}
