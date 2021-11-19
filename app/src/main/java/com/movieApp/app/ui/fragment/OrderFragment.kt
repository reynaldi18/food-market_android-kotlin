package com.movieApp.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.movieApp.app.core.CoreFragment
import com.movieApp.app.databinding.FragmentOrderBinding
import com.movieApp.app.ui.vm.OrderVm
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class OrderFragment : CoreFragment() {
    private lateinit var bind: FragmentOrderBinding
    private val vm: OrderVm by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentOrderBinding.inflate(inflater, container, false)
//        setupToolbar(bind.toolbar)
        bind.vm = vm
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nav = view.findNavController()
    }
}
