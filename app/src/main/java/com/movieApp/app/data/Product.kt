package com.movieApp.app.data

import com.google.gson.annotations.SerializedName
import com.movieApp.app.core.CoreData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("price")
    val price: Long = 0L,
    @SerializedName("rate")
    val rate: Double? = null,
    @SerializedName("photo")
    val photo: String? = null,
) : CoreData() {
    companion object {
        const val STATUS_NEW = "new"
        const val STATUS_POPULAR = "popular"
        const val STATUS_RECOMMENDED = "recommended"
    }
}
