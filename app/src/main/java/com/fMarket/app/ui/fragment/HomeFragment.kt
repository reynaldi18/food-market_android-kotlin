package com.fMarket.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.viewpager.widget.ViewPager
import com.fMarket.app.R
import com.fMarket.app.adapter.ViewPagerAdapter
import com.fMarket.app.core.CoreFragment
import com.fMarket.app.databinding.FragmentHomeBinding
import com.fMarket.app.databinding.FragmentSignUpBinding
import com.fMarket.app.ui.vm.HomeVm
import com.fMarket.app.ui.vm.SignUpVm
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class HomeFragment : CoreFragment() {
    private lateinit var bind: FragmentHomeBinding
    private val vm: HomeVm by viewModel()
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var productsNewFragment: ProductListFragment
    private lateinit var productsPopularFragment: ProductListFragment
    private lateinit var productsRecommendedFragment: ProductListFragment

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
        initTab()
    }

    private fun initTab() {
        productsNewFragment = ProductListFragment()
        productsPopularFragment = ProductListFragment()
        productsRecommendedFragment = ProductListFragment()

        adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(productsNewFragment, getString(R.string.label_new_product))
        adapter.addFragment(productsPopularFragment, getString(R.string.label_popular_product))
        adapter.addFragment(productsRecommendedFragment, getString(R.string.label_recommended_product))
        bind.vpMain.adapter = adapter
        bind.tlMain.setupWithViewPager(bind.vpMain)
        bind.vpMain.addOnPageChangeListener(
            object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int,
                ) {
                    // Page Scrolled
                }

                override fun onPageSelected(position: Int) {
                    vm.currentItem = position
                }

                override fun onPageScrollStateChanged(state: Int) {
                    // Page Scroll State Changed
                }
            }
        )
    }
}
