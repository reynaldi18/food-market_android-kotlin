package com.movieApp.app.data

import com.google.gson.annotations.SerializedName
import com.movieApp.app.core.CoreData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Init(
        @SerializedName("initial_id")
        val initialId: String? = null,
) : CoreData()