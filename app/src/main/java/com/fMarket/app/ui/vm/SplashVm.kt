package com.fMarket.app.ui.vm

import com.fMarket.app.core.CoreVm
import com.fMarket.app.helper.SingleLiveEvent
import com.fMarket.app.repo.UserRepo

internal class SplashVm(
    repo: UserRepo,
) : CoreVm() {
    private var loggedIn: Boolean = repo.isLoggedIn()
    var showSignIn = SingleLiveEvent<Void>()
    var showDashboard = SingleLiveEvent<Void>()

    fun init() {
        if (loggedIn) showDashboard.call()
        else showSignIn.call()
    }
}
