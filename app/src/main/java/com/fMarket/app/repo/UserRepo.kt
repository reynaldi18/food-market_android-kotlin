package com.fMarket.app.repo

import android.util.Log
import com.fMarket.app.api.ApiService
import com.fMarket.app.api.Resource
import com.fMarket.app.api.request.SignInReq
import com.fMarket.app.constant.Session
import com.fMarket.app.core.CoreRepo
import com.fMarket.app.data.Auth
import com.fMarket.app.data.Init
import com.fMarket.app.data.User
import com.fMarket.app.storage.SessionStorage
import java.io.IOException

class UserRepo(val api: ApiService, val session: SessionStorage) : CoreRepo() {
    suspend fun initApps(): Resource<Init> = try {
        val res = api.init()
        if (res.isSuccess()) Resource.Success(res.body())
        else Resource.Error(getErrorMessage(res))
    } catch (e: IOException) {
        Resource.Error(getErrorMessage(e))
    }

    suspend fun signIn(email: String, password: String): Resource<Auth> = try {
        val res = api.signIn(SignInReq(email, password))
        if (res.isSuccess()) {
            val data = res.body()?.data
            saveAuth(data)
            fetchUser()
            Resource.Success(data)
        } else Resource.Error(getErrorMessage(res))
    } catch (e: IOException) {
        Resource.Error(getErrorMessage(e))
    }

    private suspend fun fetchUser(): Resource<User> = try {
        val res = api.getProfile()
        if (res.isSuccess()) {
            val data = res.body()?.data
            saveUser(data)
            Resource.Success()
        } else Resource.Error(getErrorMessage(res))
    } catch (e: IOException) {
        Resource.Error(getErrorMessage(e))
    }

    private fun saveUser(data: User?) = session.put(Session.USER, data)

    fun getUser(): User? = session.get(Session.USER)

    private fun saveAuth(data: Auth?) {
        session.put(Session.AUTH, data)
    }

    private fun getAuth(): Auth? = session.get(Session.AUTH)

    fun isLoggedIn(): Boolean {
        val auth = getAuth()
        return auth != null
    }

    fun setMessagingToken(token: String) {
        session.put(Session.MESSAGING_TOKEN, token)
        Log.d("FIREBASE_TOKEN", token)
    }

    private fun getMessagingToken(): String? = session.get(Session.MESSAGING_TOKEN)
}
