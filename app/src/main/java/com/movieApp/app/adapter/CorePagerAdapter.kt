package com.movieApp.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewpager.widget.PagerAdapter
import com.movieApp.app.BR

class CorePagerAdapter(context: Context, private val layoutId: Int) : PagerAdapter() {
    var items: List<*>? = null
    var onItemClick = fun(_: View, _: Int): Unit = Unit
    private var mLayoutInflater: LayoutInflater? = null

    init {
        mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int = items?.size ?: 0

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            mLayoutInflater!!,
            layoutId,
            container,
            false
        )
        binding.setVariable(BR.data, items?.get(position))
        binding.setVariable(BR.position, position)
        binding.setVariable(BR.handler, Handler())
        container.addView(binding.root)
        return binding.root
    }

    override fun isViewFromObject(view: View, value: Any): Boolean = view == value as View

    override fun destroyItem(container: ViewGroup, position: Int, value: Any) {
        container.removeView(value as View?)
    }

    inner class Handler {
        fun onClick(view: View, position: Int) {
            onItemClick(view, position)
        }
    }
}
