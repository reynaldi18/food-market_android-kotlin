package com.fMarket.app.helper

import android.view.View
import android.widget.ViewFlipper
import androidx.databinding.BindingAdapter

@BindingAdapter("app:visible")
fun View.resId(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:displayedChild")
fun setDisplayedChild(view: ViewFlipper, index: Int) {
    view.displayedChild = index
}
