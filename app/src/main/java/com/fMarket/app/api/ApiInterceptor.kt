package com.fMarket.app.api

import com.fMarket.app.storage.SessionStorage
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.net.SocketTimeoutException

internal class ApiInterceptor : Interceptor, KoinComponent {
    private val session: SessionStorage by inject()

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val authHeader = chain.request().headers[ApiService.HEADER_AUTHORIZATION]
        if (authHeader.isNullOrEmpty()) {
            builder.addHeader(ApiService.HEADER_AUTHORIZATION, session.buildAuthToken())
        }
        val request = builder.build()
        return try {
            chain.proceed(request)
        } catch (se3: SocketTimeoutException) {
            chain.proceed(request)
        }
    }
}
