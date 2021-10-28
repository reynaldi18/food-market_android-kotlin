package com.fMarket.app.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.fMarket.app.core.CoreFragment
import com.fMarket.app.databinding.*
import com.fMarket.app.ui.vm.ProductListVm
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ProductListFragment : CoreFragment() {
    var status: String = ""
    private lateinit var bind: FragmentProductListBinding
    private val vm: ProductListVm by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        vm.getProducts(this.status)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentProductListBinding.inflate(inflater, container, false)
//        setupToolbar(bind.toolbar)
        bind.vm = vm
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nav = view.findNavController()
    }
}
