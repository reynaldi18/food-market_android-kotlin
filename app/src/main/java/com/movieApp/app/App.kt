package com.movieApp.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.orhanobut.hawk.Hawk
import com.movieApp.app.module.NetworkModule.networkingModule
import com.movieApp.app.module.RepoModule
import com.movieApp.app.module.StorageModule.storageModule
import com.movieApp.app.module.VmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.KoinContextHandler
import org.koin.core.context.startKoin

@Suppress("unused")
class App : Application() {
    override fun onCreate() {
        if (KoinContextHandler.getOrNull() == null) {
            startKoin {
                androidContext(this@App)
                modules(
                    listOf(
                        VmModule.vmodelModule,
                        RepoModule.repositoryModule,
                        networkingModule,
                        storageModule,
//                        DbModule.databaseModule
                    )
                )
            }
        }
        super.onCreate()
        Hawk.init(this).build()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                NotificationChannel(getString(R.string.channel_id), name, importance).apply {
                    description = descriptionText
                }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
