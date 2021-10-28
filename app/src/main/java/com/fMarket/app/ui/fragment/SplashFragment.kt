package com.fMarket.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.fMarket.app.R
import com.fMarket.app.core.CoreFragment
import com.fMarket.app.databinding.FragmentSplashBinding
import com.fMarket.app.ui.vm.SplashVm
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class SplashFragment : CoreFragment() {
    private lateinit var bind: FragmentSplashBinding
    private val vm: SplashVm by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentSplashBinding.inflate(inflater, container, false)
        bind.vm = vm
        vm.init()
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nav = view.findNavController()
        vm.showSignIn.observe(viewLifecycleOwner) {
            nav.navigate(R.id.action_splashFragment_to_signInFragment)
        }
        vm.showDashboard.observe(viewLifecycleOwner) {
            nav.navigate(R.id.action_splashFragment_to_dashboardFragment)
        }
    }
}
