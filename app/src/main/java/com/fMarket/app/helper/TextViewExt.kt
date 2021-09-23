package com.fMarket.app.helper

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:strike")
fun setStrike(view: TextView, show: Boolean) {
    view.paintFlags = if (show) {
        view.paintFlags or STRIKE_THRU_TEXT_FLAG
    } else {
        view.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
    }
}