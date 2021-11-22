package com.movieApp.app.data

import com.google.gson.annotations.SerializedName
import com.movieApp.app.core.CoreData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthorDetail(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("avatar_path")
    val avatarPath: String? = null,
    @SerializedName("rating")
    val rating: Double? = null,
) : CoreData() {
    fun getRate() = rating?.div(2.0)
}