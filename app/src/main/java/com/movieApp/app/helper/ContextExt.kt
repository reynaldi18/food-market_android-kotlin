package com.movieApp.app.helper

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.movieApp.app.BuildConfig
import org.koin.core.context.KoinContextHandler


fun getAppContext() = KoinContextHandler.get().get<Application>()
fun getString(id: Int) = getAppContext().getString(id)

fun String?.filterEmpty(defaultValue: String = ""): String {
    return this ?: defaultValue
}

fun isMock() = BuildConfig.FLAVOR.contains("mock", true)
fun isProd() = BuildConfig.FLAVOR.contains("prod", true)

fun <T> Context.getMockResponse(path: String, responseObj: Class<T>): T {
    val inputStream = assets.open(path)
    val strMockResponse = inputStream.bufferedReader().use { it.readText() }
    val mockResponse = Gson().fromJson(strMockResponse, responseObj)
    inputStream.close()
    return mockResponse
}

fun Context.getMockResponse(path: String): String {
    val inputStream = assets.open(path)
    return inputStream.bufferedReader().use { it.readText() }
}