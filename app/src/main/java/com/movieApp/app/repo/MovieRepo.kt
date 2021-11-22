package com.movieApp.app.repo

import com.movieApp.app.BuildConfig
import com.movieApp.app.api.ApiService
import com.movieApp.app.api.Resource
import com.movieApp.app.constant.Config
import com.movieApp.app.core.CoreRepo
import com.movieApp.app.data.Movie
import com.movieApp.app.data.Review
import com.movieApp.app.data.Videos
import com.movieApp.app.storage.SessionStorage
import java.io.IOException

class MovieRepo(val api: ApiService, val session: SessionStorage) : CoreRepo() {
    suspend fun fetchDiscover(page: Int): Resource<List<Movie>> = try {
        val res = api.getListDiscover(
            BuildConfig.API_KEY,
            Config.LANGUAGE,
            page
        )
        if (res.isSuccess()) {
            val data = res.body()?.results
            Resource.Success(data)
        } else Resource.Error(getErrorMessage(res))
    } catch (e: IOException) {
        Resource.Error(getErrorMessage(e))
    }

    suspend fun fetchDetail(id: Int?): Resource<Movie> = try {
        val res = api.getMovieDetail(
            id,
            BuildConfig.API_KEY
        )
        if (res.isSuccess()) {
            val data = res.body()
            Resource.Success(data)
        } else Resource.Error(getErrorMessage(res))
    } catch (e: IOException) {
        Resource.Error(getErrorMessage(e))
    }

    suspend fun fetchReviews(id: Int?, page: Int): Resource<Review> = try {
        val res = api.getReview(
            id,
            BuildConfig.API_KEY,
            page
        )
        if (res.isSuccess()) {
            val data = res.body()
            Resource.Success(data)
        } else Resource.Error(getErrorMessage(res))
    } catch (e: IOException) {
        Resource.Error(getErrorMessage(e))
    }

    suspend fun fetchVideos(id: Int?): Resource<Videos> = try {
        val res = api.getVideos(
            id,
            BuildConfig.API_KEY
        )
        if (res.isSuccess()) {
            val data = res.body()
            Resource.Success(data)
        } else Resource.Error(getErrorMessage(res))
    } catch (e: IOException) {
        Resource.Error(getErrorMessage(e))
    }
}
