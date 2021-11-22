package com.movieApp.app.data

import com.google.gson.annotations.SerializedName
import com.movieApp.app.core.CoreData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String? = null,
    @SerializedName("name")
    val name: String? = null,
) : CoreData()