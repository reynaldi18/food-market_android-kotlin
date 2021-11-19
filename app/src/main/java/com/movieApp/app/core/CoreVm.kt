package com.movieApp.app.core

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.movieApp.app.R
import com.movieApp.app.helper.SingleLiveEvent
import com.movieApp.app.helper.getAppContext

/**
 * https://developer.android.com/topic/libraries/architecture/viewmodel
 * This viewmodel implemented only for API related
 */
open class CoreVm : ViewModel() {
    var onSuccess = SingleLiveEvent<String>()
    var onFailed = SingleLiveEvent<String>()
    var showProgress = SingleLiveEvent<Boolean>()
    var showToast = SingleLiveEvent<String>()
    var showSnackBar = SingleLiveEvent<String>()
    var showSnackBarError = SingleLiveEvent<String>()

    companion object {
        // View flipper displayed child
        const val STATE_HAS_DATA = 0
        const val STATE_PROGRESS = 1
        const val STATE_NO_DATA = 2
        const val STATE_FAILED = 3
    }

    fun ObservableField<String>.isValidPassword(): Boolean {
        val text = get().orEmpty()
        return !text.lessThan(R.integer.min_password) && !text.moreThan(R.integer.max_password)
    }

    fun ObservableField<String>.isValidName(): Boolean {
        val text = get().orEmpty()
        return !text.lessThan(R.integer.min_name) && !text.moreThan(R.integer.max_name) && !containsNumber(
            text
        )
    }

    fun ObservableField<String>.isValidEmail(): Boolean {
        val text = get().orEmpty()
        return text.matches(Regex("^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})\$"))
    }

    fun ObservableField<String>.isValidPhone(): Boolean {
        val text = get().orEmpty()
        return !text.lessThan(R.integer.min_phone) && !text.moreThan(R.integer.max_phone)
    }

    fun String?.lessThan(minLengthResId: Int): Boolean {
        if (isNullOrEmpty()) return true
        val length = getAppContext().resources.getInteger(minLengthResId)
        return this.length < length
    }

    private fun String?.moreThan(maxLengthResId: Int): Boolean {
        if (isNullOrEmpty()) return false
        val length = getAppContext().resources.getInteger(maxLengthResId)
        return this.length > length
    }

    fun String?.equalsValue(lengthResId: Int): Boolean {
        if (isNullOrEmpty()) return false
        val length = getAppContext().resources.getInteger(lengthResId)
        return this.length == length
    }

    protected fun ObservableField<String>.isNotEmpty(): Boolean {
        val text = get().orEmpty()
        return text.isNotEmpty()
    }

    private fun containsNumber(string: String): Boolean = string.matches(Regex(".*[0-9].*"))
}
