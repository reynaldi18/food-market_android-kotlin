package com.fMarket.app.module

import com.fMarket.app.ui.vm.MainVm
import com.fMarket.app.ui.vm.SignInVm
import com.fMarket.app.ui.vm.SignUpVm
import com.fMarket.app.ui.vm.SplashVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object VmModule {
    val vmodelModule = module {
        viewModel { SplashVm()}
        viewModel { SignInVm() }
        viewModel { SignUpVm() }
        viewModel { MainVm() }
    }
}
