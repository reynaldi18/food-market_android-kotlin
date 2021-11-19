package com.movieApp.app.helper

import androidx.databinding.Observable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import com.movieApp.app.listener.ObservableListListener

fun <T> ObservableField<T>.onChanged(
    onChanged: () -> Unit,
) {
    this.addOnPropertyChangedCallback(
        object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) = onChanged()
        }
    )
}

fun <T> ObservableArrayList<T>.onChanged(onChanged: (ObservableList<T>?) -> Unit) {
    val listener = ObservableListListener<T>()
    listener.onItemChanged = onChanged
    addOnListChangedCallback(listener)
}
