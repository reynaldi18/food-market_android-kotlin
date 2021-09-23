package com.fMarket.app.repo

import android.util.Log
import com.fMarket.app.api.ApiService
import com.fMarket.app.api.Resource
import com.fMarket.app.constant.Session
import com.fMarket.app.core.CoreRepo
import com.fMarket.app.data.Init
import com.fMarket.app.storage.SessionStorage
import java.io.IOException

class UserRepo(val api: ApiService, val session: SessionStorage) : CoreRepo() {
    private var username: String = ""
    private var password: String = ""

    suspend fun initApps(): Resource<Init> = try {
        val res = api.init()
        if (res.isSuccess()) Resource.Success(res.body())
        else Resource.Error(getErrorMessage(res))
    } catch (e: IOException) {
        Resource.Error(getErrorMessage(e))
    }

    fun setMessagingToken(token: String) {
        session.put(Session.MESSAGING_TOKEN, token)
        Log.d("FIREBASE_TOKEN", token)
    }

    private fun getMessagingToken(): String? = session.get(Session.MESSAGING_TOKEN)
}
