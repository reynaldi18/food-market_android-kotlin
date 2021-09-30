package com.fMarket.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.fMarket.app.R
import com.fMarket.app.core.CoreFragment
import com.fMarket.app.databinding.FragmentMainBinding

internal class DashboardFragment : CoreFragment() {
    private lateinit var bind: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentMainBinding.inflate(inflater, container, false)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bind.navView.setupWithNavController(navController)
        return bind.root
    }
}
