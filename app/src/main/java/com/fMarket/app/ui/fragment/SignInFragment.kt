package com.fMarket.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.fMarket.app.R
import com.fMarket.app.core.CoreFragment
import com.fMarket.app.databinding.FragmentSignInBinding
import com.fMarket.app.helper.Common
import com.fMarket.app.ui.vm.SignInVm
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class SignInFragment : CoreFragment() {
    private lateinit var bind: FragmentSignInBinding
    private val vm: SignInVm by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentSignInBinding.inflate(inflater, container, false)
        setupToolbar(bind.toolbar)
        bind.vm = vm
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nav = view.findNavController()
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.signInFragment))
        bind.toolbar.setupWithNavController(nav, appBarConfiguration)
        bind.bSignUp.setOnClickListener { nav.navigate(R.id.action_signInFragment_to_signUpFragment) }
        vm.showSnackBar.observe(viewLifecycleOwner) { Common.showSnackBar(bind.clMain, it) }
        bind.bSignIn.setOnClickListener { vm.validate() }
    }
}
