package com.fMarket.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.fMarket.app.core.CoreFragment
import com.fMarket.app.databinding.FragmentSignUpBinding
import com.fMarket.app.ui.vm.SignUpVm
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class SignUpFragment : CoreFragment() {
    private lateinit var bind: FragmentSignUpBinding
    private val vm: SignUpVm by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentSignUpBinding.inflate(inflater, container, false)
        setupToolbar(bind.toolbar)
        bind.vm = vm
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nav = view.findNavController()
    }
}
