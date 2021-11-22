package com.movieApp.app.data

import com.google.gson.annotations.SerializedName
import com.movieApp.app.core.CoreData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("page")
    val page: Long? = null,
    @SerializedName("results")
    val results: List<Author>? = null,
    @SerializedName("total_pages")
    val totalPages: String? = null,
    @SerializedName("total_results")
    val totalResults: String? = null,
) : CoreData()
