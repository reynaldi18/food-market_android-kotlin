package com.fMarket.app.data

import com.google.gson.annotations.SerializedName
import com.fMarket.app.core.CoreData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductUnit(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null
) : CoreData()
