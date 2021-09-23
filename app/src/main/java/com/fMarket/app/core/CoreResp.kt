package com.fMarket.app.core

import com.google.gson.annotations.SerializedName

/**
 * Created by MuhammadLucky on 14/05/2018.
 */
open class CoreResp<T>(
    @SerializedName("error")
    val error: String? = null,
    @SerializedName("error_description")
    val errorDescription: String? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    var data: T? = null,
)
