package com.fMarket.app.ui.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.fMarket.app.R
import com.fMarket.app.core.CoreVm
import com.fMarket.app.helper.getString
import kotlinx.coroutines.launch

internal class SignInVm : CoreVm() {
    var email = ObservableField("")
    var password = ObservableField("")

    fun validate() = viewModelScope.launch {
        val errorResId = when {
            !email.isValidEmail() -> R.string.error_invalid_email
            !password.isValidPassword() -> R.string.error_invalid_password
            else -> 0
        }

        if (errorResId != 0) {
            showSnackBar.value = getString(errorResId)
            return@launch
        }
    }
}
