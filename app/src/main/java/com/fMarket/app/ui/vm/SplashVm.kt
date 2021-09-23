package com.fMarket.app.ui.vm

import com.fMarket.app.core.CoreVm
import com.fMarket.app.helper.SingleLiveEvent

internal class SplashVm() : CoreVm() {
    var showPageOne = SingleLiveEvent<Void>()

    fun init() {
        showPageOne.call()
    }
}
