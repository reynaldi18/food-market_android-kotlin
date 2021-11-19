package com.movieApp.app.module

import com.movieApp.app.ui.vm.HomeVm
import com.movieApp.app.ui.vm.MainVm
import com.movieApp.app.ui.vm.OrderVm
import com.movieApp.app.ui.vm.ProductListVm
import com.movieApp.app.ui.vm.ProfileVm
import com.movieApp.app.ui.vm.SignInVm
import com.movieApp.app.ui.vm.SignUpVm
import com.movieApp.app.ui.vm.SplashVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object VmModule {
    val vmodelModule = module {
        viewModel { SplashVm(get())}
        viewModel { SignInVm(get()) }
        viewModel { SignUpVm(get()) }
        viewModel { MainVm() }
        viewModel { HomeVm(get(), get()) }
        viewModel { OrderVm() }
        viewModel { ProfileVm() }
        viewModel { ProductListVm(get()) }
    }
}
