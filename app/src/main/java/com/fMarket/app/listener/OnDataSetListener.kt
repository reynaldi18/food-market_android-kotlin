package com.fMarket.app.listener

import android.os.Parcel
import android.os.Parcelable

abstract class OnDataSetListener : Parcelable {
    abstract fun onDataSet(data: Any?)
    override fun describeContents() = 0
    override fun writeToParcel(p0: Parcel?, p1: Int) {
        // Some code
    }
}
