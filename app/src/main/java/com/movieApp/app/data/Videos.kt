package com.movieApp.app.data

import com.google.gson.annotations.SerializedName
import com.movieApp.app.core.CoreData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Videos(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("results")
    val results: List<Video>? = null,
) : CoreData()
