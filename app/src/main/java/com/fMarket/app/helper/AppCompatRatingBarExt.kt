package com.fMarket.app.helper

import android.widget.RatingBar
import androidx.databinding.BindingAdapter

@BindingAdapter("android:rating")
fun setRating(view: RatingBar?, rating: Double) {
    if (view != null) {
        val rate = rating.toFloat()
        view.rating = rate
    }
}