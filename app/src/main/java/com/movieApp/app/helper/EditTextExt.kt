package com.movieApp.app.helper

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("android:text")
fun setText(view: TextInputEditText, value: Long) {
    val text = if (value != 0L) value.toString() else ""
    view.setText(text)
}

@InverseBindingAdapter(attribute = "android:text")
fun getText(view: TextInputEditText): Long {
    var text = view.text.toString().replace(".", "")
    if (text.isEmpty()) text = "0"
    return text.toLong()
}

@BindingAdapter("withCurrencyFormatter")
fun EditText.withCurrencyFormatter(formatter: Boolean) {
    if (formatter)
        addTextChangedListener(NumberTextWatcher(this))
}