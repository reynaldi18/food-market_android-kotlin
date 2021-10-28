package com.fMarket.app.module

import com.fMarket.app.ui.vm.HomeVm
import com.fMarket.app.ui.vm.MainVm
import com.fMarket.app.ui.vm.OrderVm
import com.fMarket.app.ui.vm.ProductListVm
import com.fMarket.app.ui.vm.ProfileVm
import com.fMarket.app.ui.vm.SignInVm
import com.fMarket.app.ui.vm.SignUpVm
import com.fMarket.app.ui.vm.SplashVm
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
