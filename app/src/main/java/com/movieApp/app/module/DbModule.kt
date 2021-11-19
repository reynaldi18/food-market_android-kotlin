package com.movieApp.app.module

import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

object DbModule {
    val databaseModule = module {
        single { FirebaseFirestore.getInstance() }
    }
}
