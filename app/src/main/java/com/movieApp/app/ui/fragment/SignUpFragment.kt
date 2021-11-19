package com.movieApp.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.movieApp.app.core.CoreFragment
import com.movieApp.app.databinding.FragmentSignUpBinding
import com.movieApp.app.helper.Common
import com.movieApp.app.ui.vm.SignUpVm
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
        vm.showProgress.observe(viewLifecycleOwner) {
            if (it) Common.showProgressDialog(context = requireContext())
            else Common.dismissProgressDialog()
        }
        vm.showSnackBar.observe(viewLifecycleOwner) { Common.showSnackBar(bind.clMain, it) }
        vm.onFailed.observe(viewLifecycleOwner) { Common.showSnackBarError(bind.clMain, it) }
//        vm.onSuccess.observe(viewLifecycleOwner) { nav.navigate(R.id.action_signInFragment_to_dashboardFragment) }
        bind.bSignUp.setOnClickListener { vm.validate() }
    }
}
