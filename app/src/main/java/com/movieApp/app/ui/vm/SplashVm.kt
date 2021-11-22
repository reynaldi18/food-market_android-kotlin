package com.movieApp.app.ui.vm

import com.movieApp.app.core.CoreVm
import com.movieApp.app.helper.SingleLiveEvent

internal class SplashVm() : CoreVm() {
    var showHome = SingleLiveEvent<Void>()

    fun init() {
        showHome.call()
    }
}
