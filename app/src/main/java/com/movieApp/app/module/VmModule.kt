package com.movieApp.app.module

import com.movieApp.app.ui.vm.HomeVm
import com.movieApp.app.ui.vm.MainVm
import com.movieApp.app.ui.vm.MovieDetailVm
import com.movieApp.app.ui.vm.SplashVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object VmModule {
    val vmodelModule = module {
        viewModel { SplashVm()}
        viewModel { MainVm() }
        viewModel { HomeVm(get()) }
        viewModel { MovieDetailVm(get()) }
    }
}
