package com.fMarket.app.api.request

import com.fMarket.app.core.CoreReq
import com.google.gson.annotations.SerializedName

data class SignUpReq(
    @SerializedName("photo")
    val photo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("houseNumber")
    val houseNumber: String,
    @SerializedName("city")
    val city: String,
) : CoreReq()
