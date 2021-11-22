package com.movieApp.app.module

import com.movieApp.app.repo.MovieRepo
import org.koin.dsl.module

object RepoModule {
    val repositoryModule = module {
        single { MovieRepo(get(), get()) }
    }
}
