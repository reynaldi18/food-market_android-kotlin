package com.fMarket.app.helper

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fMarket.app.R

@BindingAdapter("app:resId")
fun ImageView.resId(drawableResId: Int) {
    setImageDrawable(ContextCompat.getDrawable(getAppContext(), drawableResId))
}

@BindingAdapter("app:srcUrl")
fun ImageView.srcUrl(url: String?) = loadResourceImage(url ?: "", R.drawable.bg_logo)

fun ImageView.loadResourceImage(url: String, placeholder: Int) {
    Glide.with(context)
        .load(url)
        .apply(RequestOptions().placeholder(placeholder))
        .centerCrop()
        .into(this)
}

@BindingAdapter("app:srcUrlCircle")
fun ImageView.circleLoadImage(imageSource: String?) {
    urCircleImage(imageSource, R.drawable.bg_placeholder_circle)
}

fun ImageView.urCircleImage(imageSource: String?, placeholder: Int) {
    Glide.with(context)
        .load(imageSource.orEmpty())
        .apply(RequestOptions.circleCropTransform())
        .placeholder(placeholder)
        .into(this)
}
