package com.movieApp.app.ui.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.movieApp.app.R
import com.movieApp.app.api.Resource
import com.movieApp.app.core.CoreVm
import com.movieApp.app.helper.getString
import com.movieApp.app.repo.UserRepo
import kotlinx.coroutines.launch

internal class SignUpVm(
    private val repo: UserRepo
) : CoreVm() {
    var photo = ObservableField("")
    var name = ObservableField("")
    var email = ObservableField("")
    var password = ObservableField("")
    var phone = ObservableField("")
    var address = ObservableField("")
    var houseNumber = ObservableField("")
    var city = ObservableField("")

    fun validate() = viewModelScope.launch {
        val errorResId = when {
            !name.isValidName() -> R.string.error_invalid_name
            !email.isValidEmail() -> R.string.error_invalid_email
            !password.isValidPassword() -> R.string.error_invalid_password
            !phone.isValidPhone() -> R.string.error_invalid_phone
            !address.isNotEmpty() -> R.string.error_required_address
            !houseNumber.isNotEmpty() -> R.string.error_required_house_number
            !city.isNotEmpty() -> R.string.error_required_city
            else -> 0
        }

        if (errorResId != 0) {
            showSnackBar.value = getString(errorResId)
            return@launch
        }

        showProgress.value = true
        val res = repo.signUp(
            phone.get().orEmpty(),
            name.get().orEmpty(),
            email.get().orEmpty(),
            password.get().orEmpty(),
            phone.get().orEmpty(),
            address.get().orEmpty(),
            houseNumber.get().orEmpty(),
            city.get().orEmpty()
        )
        showProgress.value = false
        when (res) {
            is Resource.Error -> onFailed.value = res.message
            is Resource.Success -> onSuccess.call()
        }
    }
}
