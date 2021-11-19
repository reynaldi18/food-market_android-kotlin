package com.movieApp.app.module

import com.movieApp.app.repo.ProductRepo
import com.movieApp.app.repo.UserRepo
import org.koin.dsl.module

object RepoModule {
    val repositoryModule = module {
        single { UserRepo(get(), get()) }
        single { ProductRepo(get(), get()) }
    }
}
