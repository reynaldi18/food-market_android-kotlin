package com.fMarket.app.listener

import androidx.databinding.ObservableList
import androidx.databinding.ObservableList.OnListChangedCallback

open class ObservableListListener<T> : OnListChangedCallback<ObservableList<T>>() {

    var onItemChanged = fun(_: ObservableList<T>?): Unit = Unit

    override fun onChanged(sender: ObservableList<T>?) {
        sender?.isEmpty()
    }

    override fun onItemRangeChanged(
        sender: ObservableList<T>?,
        positionStart: Int,
        itemCount: Int,
    ) {
        itemCount > 0
    }

    override fun onItemRangeInserted(
        sender: ObservableList<T>?,
        positionStart: Int,
        itemCount: Int,
    ) {
        onItemChanged(sender)
    }

    override fun onItemRangeMoved(
        sender: ObservableList<T>?,
        fromPosition: Int,
        toPosition: Int,
        itemCount: Int,
    ) {
        itemCount > 0
    }

    override fun onItemRangeRemoved(
        sender: ObservableList<T>?,
        positionStart: Int,
        itemCount: Int,
    ) {
        itemCount > 0
    }
}
