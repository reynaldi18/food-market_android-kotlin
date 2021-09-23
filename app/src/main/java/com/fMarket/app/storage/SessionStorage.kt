package com.fMarket.app.storage

import android.content.Context
import com.orhanobut.hawk.Hawk
import com.fMarket.app.constant.Session
import com.fMarket.app.constant.Session.MESSAGING_TOKEN
import com.fMarket.app.data.Auth
import com.fMarket.app.helper.filterEmpty

open class SessionStorage(var context: Context) {
    fun <T> put(key: String, value: T) = Hawk.put(key, value)
    fun <T> get(key: String): T? = Hawk.get<T>(key)
    fun deleteAll() {
        val fcmToken: String = Hawk.get<String>(MESSAGING_TOKEN).filterEmpty()
        Hawk.deleteAll()
        Hawk.put(MESSAGING_TOKEN, fcmToken)
    }

    fun buildAuthToken(): String {
        val auth = get<Auth>(Session.AUTH)
        val bearer = auth?.tokenType
        val token = auth?.accessToken
        return "$bearer $token"
    }
}
