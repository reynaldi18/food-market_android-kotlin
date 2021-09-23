package com.fMarket.app.data

import com.google.gson.annotations.SerializedName
import com.fMarket.app.core.CoreData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("phone_number")
    val phoneNumber: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("phone_number_verified_at")
    val phoneNumberVerifiedAt: String? = null,
    @SerializedName("role")
    var role: String? = null,
) : CoreData() {}
