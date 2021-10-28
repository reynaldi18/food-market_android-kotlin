package com.fMarket.app.repo

import android.util.Log
import com.fMarket.app.api.ApiService
import com.fMarket.app.api.Resource
import com.fMarket.app.api.request.SignInReq
import com.fMarket.app.api.request.SignUpReq
import com.fMarket.app.constant.Session
import com.fMarket.app.core.CoreRepo
import com.fMarket.app.data.Auth
import com.fMarket.app.data.Init
import com.fMarket.app.data.Product
import com.fMarket.app.data.User
import com.fMarket.app.storage.SessionStorage
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
