package com.fMarket.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.fMarket.app.core.CoreFragment
import com.fMarket.app.databinding.FragmentHomeBinding
import com.fMarket.app.databinding.FragmentOrderBinding
import com.fMarket.app.databinding.FragmentProfileBinding
import com.fMarket.app.databinding.FragmentSignUpBinding
import com.fMarket.app.ui.vm.HomeVm
import com.fMarket.app.ui.vm.OrderVm
import com.fMarket.app.ui.vm.ProfileVm
import com.fMarket.app.ui.vm.SignUpVm
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ProfileFragment : CoreFragment() {
    private lateinit var bind: FragmentProfileBinding
    private val vm: ProfileVm by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentProfileBinding.inflate(inflater, container, false)
//        setupToolbar(bind.toolbar)
        bind.vm = vm
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nav = view.findNavController()
    }
}
