package com.movieApp.app.module

import com.movieApp.app.storage.SessionStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object StorageModule {
    val storageModule = module {
        single { SessionStorage(androidContext()) }
    }
}
