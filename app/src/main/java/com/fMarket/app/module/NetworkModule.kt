package com.fMarket.app.module

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.google.gson.GsonBuilder
import com.fMarket.app.api.ApiInterceptor
import com.fMarket.app.api.ApiService
import com.fMarket.app.constant.Config.REQUEST_TIMEOUT
import com.fMarket.app.helper.JNIUtil
import com.fMarket.app.helper.NullOnEmptyConverterFactory
import com.fMarket.app.helper.isProd
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    val networkingModule = module {
        single { provideOkHttpClient() }
        single { provideApiService(get()) }
        single { provideAnalytics() }
    }

    private fun provideAnalytics() = Firebase.analytics

    private fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuild = OkHttpClient.Builder()
        okHttpBuild.apply {
            connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(ApiInterceptor())
            if (!isProd()) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(logging)
            }
        }
        return okHttpBuild.build()
    }

    private fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(JNIUtil.apiEndpoint())
            .client(okHttpClient)
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().serializeNulls().create())
            )
            .build().create(ApiService::class.java)
    }
}
