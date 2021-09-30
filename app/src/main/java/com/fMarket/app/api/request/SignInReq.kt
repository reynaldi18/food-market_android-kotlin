package com.fMarket.app.api.request

import com.fMarket.app.core.CoreReq
import com.google.gson.annotations.SerializedName

data class SignInReq(
        @SerializedName("email")
        val email: String,
        @SerializedName("password")
        val password: String
) : CoreReq()
