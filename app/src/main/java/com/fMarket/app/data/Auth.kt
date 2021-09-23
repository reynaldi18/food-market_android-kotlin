package com.fMarket.app.data

import com.google.gson.annotations.SerializedName
import com.fMarket.app.core.CoreData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Auth(
    @SerializedName("token_type")
    val tokenType: String? = null,
    @SerializedName("expires_in")
    val expiresIn: Long? = null,
    @SerializedName("access_token")
    val accessToken: String? = null,
    @SerializedName("refresh_token")
    val refreshToken: String? = null,
) : CoreData()
