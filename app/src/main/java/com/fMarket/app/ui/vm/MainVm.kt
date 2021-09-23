package com.fMarket.app.ui.vm

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.fMarket.app.constant.Session
import com.fMarket.app.core.CoreVm
import com.fMarket.app.helper.SingleLiveEvent
import kotlinx.coroutines.launch

internal class MainVm() : CoreVm() {
//    val showStartPage = SingleLiveEvent<String>()
    /**
     * Handle deeplink when open apps
     */
    fun handleDeepLink(uri: Uri?) = viewModelScope.launch {
        if (uri == null) return@launch
//        val referral = uri.getQueryParameter(Session.PARAM_REFERRAL_CODE)
//        if (!referral.isNullOrEmpty()) showStartPage.value = referral
    }
}