package com.movieApp.app.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.movieApp.app.BR

/**
 * Common adapter to load the recyclerView
 */
class CoreListAdapter(
    @LayoutRes
    var layoutId: Int = 0
) : RecyclerView.Adapter<CoreListAdapter.Holder>() {

    // List of items that will be inflated in the layout
    var items: List<Any>? = null
    var onItemClick = fun(_: View, _: Int): Unit = Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            layoutId,
            parent,
            false
        )
        return Holder(binding)
    }

    /**
     *   Count of items inside the adapter
     */
    override fun getItemCount() = items?.size ?: 0

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items?.get(position))
    }

    inner class Holder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         *   Event handler, can be used in the row layout to register any click events,
         *   these events can be handled in the parent.
         */

        fun bind(ob: Any?) {
            binding.setVariable(BR.data, ob)
            binding.setVariable(BR.handler, this)
            binding.setVariable(BR.position, adapterPosition)
            binding.executePendingBindings()
        }

        /**
         *   Propagate clicks to parent via the event handler
         */
        fun onClick(view: View, position: Int) {
            onItemClick(view, position)
        }
    }
}
