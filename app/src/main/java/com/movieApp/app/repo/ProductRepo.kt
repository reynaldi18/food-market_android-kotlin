package com.movieApp.app.repo

import com.movieApp.app.api.ApiService
import com.movieApp.app.api.Resource
import com.movieApp.app.core.CoreRepo
import com.movieApp.app.data.Product
import com.movieApp.app.storage.SessionStorage
import java.io.IOException

class ProductRepo(val api: ApiService, val session: SessionStorage) : CoreRepo() {
    suspend fun fetchHighlight(): Resource<List<Product>> = try {
        val res = api.getHighlight()
        if (res.isSuccess()) {
            val data = res.body()?.data
            Resource.Success(data)
        } else Resource.Error(getErrorMessage(res))
    } catch (e: IOException) {
        Resource.Error(getErrorMessage(e))
    }

    suspend fun fetchNewProducts(): Resource<List<Product>> = try {
        val res = api.getNewProducts()
        if (res.isSuccess()) {
            val data = res.body()?.data
            Resource.Success(data)
        } else Resource.Error(getErrorMessage(res))
    } catch (e: IOException) {
        Resource.Error(getErrorMessage(e))
    }

    suspend fun fetchPopularProducts(): Resource<List<Product>> = try {
        val res = api.getPopularProducts()
        if (res.isSuccess()) {
            val data = res.body()?.data
            Resource.Success(data)
        } else Resource.Error(getErrorMessage(res))
    } catch (e: IOException) {
        Resource.Error(getErrorMessage(e))
    }

    suspend fun fetchRecommendedProducts(): Resource<List<Product>> = try {
        val res = api.getRecommendedProducts()
        if (res.isSuccess()) {
            val data = res.body()?.data
            Resource.Success(data)
        } else Resource.Error(getErrorMessage(res))
    } catch (e: IOException) {
        Resource.Error(getErrorMessage(e))
    }
}
