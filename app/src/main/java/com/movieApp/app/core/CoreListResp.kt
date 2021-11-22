package com.movieApp.app.core

import com.google.gson.annotations.SerializedName

open class CoreListResp<T>(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null,
    @SerializedName("results")
    var results: List<T>,
)
