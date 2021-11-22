package com.movieApp.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.movieApp.app.R
import com.movieApp.app.core.CoreFragment
import com.movieApp.app.databinding.FragmentHomeBinding
import com.movieApp.app.helper.onChanged
import com.movieApp.app.listener.EndlessOnScrollListener
import com.movieApp.app.ui.vm.HomeVm
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class HomeFragment : CoreFragment() {
    private lateinit var bind: FragmentHomeBinding
    private val vm: HomeVm by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentHomeBinding.inflate(inflater, container, false)
//        setupToolbar(bind.toolbar)
        bind.vm = vm
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nav = view.findNavController()
        vm.showProgress.observe(viewLifecycleOwner, { if (it) showProgress() else hideProgress() })
        vm.showMovie.observe(
            viewLifecycleOwner,
            {
                nav.navigate(
                    R.id.action_homeFragment_to_movieDetailFragment,
                    it.id?.let { movieId ->
                        MovieDetailFragmentArgs(
                            id = movieId
                        ).toBundle()
                    }
                )
            }
        )
        bind.srMain.setOnRefreshListener { vm.fetchDiscover(true) }
        vm.state.onChanged { bind.srMain.isRefreshing = false }
        bind.rvMain.addOnScrollListener(object : EndlessOnScrollListener() {
            override fun onLoadMore() {
                vm.fetchDiscover()
            }
        })
    }
}
