package com.movieApp.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.movieApp.app.ui.vm.MainVm
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val vm: MainVm by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleDeeplink(intent)
//        Navigation.findNavController(this, R.id.nav_host_main)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleDeeplink(intent)
    }

    /**
     * Handle deeplink with Firebase Dynamic Link
     */
    private fun handleDeeplink(newIntent: Intent?) = newIntent?.let {
        vm.handleDeepLink(newIntent.data)
    }
}
