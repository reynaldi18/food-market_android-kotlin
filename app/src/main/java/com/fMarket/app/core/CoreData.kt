package com.fMarket.app.core

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.fMarket.app.constant.Config
import com.fMarket.app.helper.DateHelper
import kotlinx.android.parcel.Parcelize

/**
 * Created by MuhammadLucky on 14/05/2018.
 */
@Parcelize
open class CoreData(
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
) : Parcelable {
    fun getDateCreatedAt() =
        DateHelper.reformat(dateText = createdAt, outputPattern = Config.DATE_OUTPUT)

    fun getDateUpdatedAt() =
        DateHelper.reformat(dateText = updatedAt, outputPattern = Config.DATE_OUTPUT)
}
