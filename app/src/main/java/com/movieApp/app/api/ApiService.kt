package com.movieApp.app.api

import com.movieApp.app.core.CoreListResp
import com.movieApp.app.data.Init
import com.movieApp.app.data.Movie
import com.movieApp.app.data.Review
import com.movieApp.app.data.Videos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
    }

    @GET("init")
    suspend fun init(): Response<Init>

    @GET("discover/movie")
    suspend fun getListDiscover(
        @Query("api_key") apiKey: String,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): Response<CoreListResp<Movie>>

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int?,
        @Query("api_key") apiKey: String
    ): Response<Movie>

    @GET("movie/{id}/reviews")
    suspend fun getReview(
        @Path("id") id: Int?,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int?
    ): Response<Review>

    @GET("movie/{id}/videos")
    suspend fun getVideos(
        @Path("id") id: Int?,
        @Query("api_key") apiKey: String
    ): Response<Videos>
}
