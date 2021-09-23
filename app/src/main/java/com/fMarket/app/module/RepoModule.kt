package com.fMarket.app.module

import com.fMarket.app.repo.UserRepo
import org.koin.dsl.module

object RepoModule {
    val repositoryModule = module {
        single { UserRepo(get(), get()) }
    }
}
