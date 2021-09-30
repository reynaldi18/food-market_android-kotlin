package com.fMarket.app.core

import com.google.gson.annotations.SerializedName

/**
 * Created by MuhammadLucky on 14/05/2018.
 */
open class CoreResp<T>(
    @SerializedName("status")
    val status: Boolean? = true,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    var data: T? = null,
)
