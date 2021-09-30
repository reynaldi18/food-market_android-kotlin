package com.fMarket.app.data

import com.google.gson.annotations.SerializedName
import com.fMarket.app.core.CoreData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Auth(
    @SerializedName("tokenType")
    val tokenType: String? = null,
    @SerializedName("expiresIn")
    val expiresIn: Long? = null,
    @SerializedName("accessToken")
    val accessToken: String? = null,
    @SerializedName("refreshToken")
    val refreshToken: String? = null,
) : CoreData()
