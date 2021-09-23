package com.fMarket.app.core

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.gson.Gson
import com.fMarket.app.R
import com.fMarket.app.data.Init
import com.fMarket.app.helper.getAppContext
import com.fMarket.app.helper.getString
import retrofit2.Response
import java.net.ConnectException

open class CoreRepo {
    fun <T> getErrorMessage(response: Response<T>?) = when {
        response?.body() != null -> getErrorMessage(response.body())
        response?.errorBody() != null ->
            try {
                val errorBody = response.errorBody()?.string()?.toGson(CoreResp::class.java)
                val message = errorBody?.message
                message
            } catch (e: java.lang.Exception) {
                getErrorMessage(e)
            }.orEmpty()
        else -> getErrorMessage()
    }

    internal fun getErrorMessage(e: Exception? = null) =
        if (e is ConnectException) getString(R.string.error_not_connection)
        else getString(R.string.error_common)

    private fun <T> getErrorMessage(body: T?): String {
        val resp = (body as CoreResp<*>?) ?: CoreResp<Init>()
        return if (resp.message.isNullOrEmpty()) getString(R.string.error_common)
        else resp.message.orEmpty()
    }

    private fun readFromFile(filename: String) =
        getAppContext().assets.open(filename).bufferedReader().use { it.readText() }

    protected fun <T> Response<T>?.isSuccess(): Boolean = this?.isSuccessful ?: false

    private fun <T> String.toGson(clazz: Class<T>): T = Gson().fromJson(this, clazz)

    internal fun <T> gsonFromFile(fileName: String, javaClass: Class<T>): T? =
        Gson().fromJson(readFromFile(fileName), javaClass)

    /** Get single firestore data */
    protected fun <T> getSingleData(task: QuerySnapshot?, java: Class<T>): T? {
        if (task?.size() ?: 0 == 0) return null
        return task?.documents?.get(0)?.toObject(java)
    }

    /** Get single firestore data */
    protected fun <T> getSingleData(snapshot: DocumentSnapshot?, java: Class<T>): T? {
        return snapshot?.toObject(java)
    }

    /** Get firestore data list */
    protected fun <T> getMultipleData(task: QuerySnapshot?, java: Class<T>): MutableList<T>? {
        if (task?.size() ?: 0 == 0) return null
        val list = mutableListOf<T>()
        task?.documents?.forEach {
            val value = it?.toObject(java)
            value?.let { it1 -> list.add(it1) }
        }
        return list
    }
}
