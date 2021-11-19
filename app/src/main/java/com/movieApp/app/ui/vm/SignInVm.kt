package com.movieApp.app.ui.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.movieApp.app.R
import com.movieApp.app.api.Resource
import com.movieApp.app.core.CoreVm
import com.movieApp.app.helper.getString
import com.movieApp.app.repo.UserRepo
import kotlinx.coroutines.launch

internal class SignInVm(
    private val repo: UserRepo
) : CoreVm() {
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

        showProgress.value = true
        val res = repo.signIn(email.get().orEmpty(), password.get().orEmpty())
        showProgress.value = false
        when (res) {
            is Resource.Error -> onFailed.value = res.message
            is Resource.Success -> onSuccess.call()
        }
    }
}
