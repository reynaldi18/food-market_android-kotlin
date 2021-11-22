package com.movieApp.app.data

import com.google.gson.annotations.SerializedName
import com.movieApp.app.constant.Config
import com.movieApp.app.core.CoreData
import com.movieApp.app.helper.DateHelper
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Author(
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("author_details")
    val authorDetails: AuthorDetail? = null,
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("url")
    val url: String? = null,
) : CoreData(){
    fun getImageAuthor() = "https://image.tmdb.org/t/p/w500/${authorDetails?.avatarPath}"

    fun getDateReview() =
        DateHelper.reformat(dateText = createdAt, outputPattern = Config.DATE_OUTPUT)
}