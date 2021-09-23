package com.fMarket.app.core

import com.google.gson.annotations.SerializedName
import com.fMarket.app.BuildConfig

/**
 * Created by MuhammadLucky on 14/05/2018.
 */
open class CoreReq {
    @SerializedName("app_version")
    val appVersion = BuildConfig.VERSION_NAME
}
