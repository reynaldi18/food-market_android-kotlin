package com.fMarket.app.data

import com.google.gson.annotations.SerializedName
import com.fMarket.app.core.CoreData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("photo")
    val photo: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("address")
    var address: String? = null,
    @SerializedName("houseNumber")
    var houseNumber: String? = null,
    @SerializedName("city")
    var city: String? = null,
) : CoreData() {}
