package com.movieApp.app.ui.vm

import com.movieApp.app.core.CoreVm
import com.movieApp.app.helper.SingleLiveEvent
import com.movieApp.app.repo.UserRepo

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
