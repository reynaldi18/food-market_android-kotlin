package com.fMarket.app.helper

import android.graphics.drawable.Drawable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fondesa.recyclerviewdivider.RecyclerViewDivider
import com.fMarket.app.core.CoreListAdapter

@BindingAdapter(
    value = ["app:gridAdapter", "app:gridSize", "app:gridVerticalOrientation"],
    requireAll = true
)
fun RecyclerView.gridAdapter(
    resourceAdapter: CoreListAdapter,
    gridSize: Int,
    verticalOrientation: Boolean? = true,
) {
    layoutManager = GridLayoutManager(
        context,
        gridSize,
        if (verticalOrientation == true) {
            GridLayoutManager.VERTICAL
        } else {
            GridLayoutManager.HORIZONTAL
        },
        false
    )
    adapter = resourceAdapter
}

@BindingAdapter("app:adapter", "app:verticalOrientation", requireAll = false)
fun RecyclerView.adapter(resourceAdapter: CoreListAdapter, verticalOrientation: Boolean) {
    layoutManager = LinearLayoutManager(
        context,
        if (verticalOrientation) {
            LinearLayoutManager.VERTICAL
        } else {
            LinearLayoutManager.HORIZONTAL
        },
        false
    )
    adapter = resourceAdapter
}

@BindingAdapter("app:dividerResId")
fun RecyclerView.dividerResId(resId: Drawable) {
    if (itemDecorationCount > 0) removeItemDecorationAt(0)
    RecyclerViewDivider.with(context)
        .drawable(resId)
        .hideLastDivider()
        .build().addTo(this)
}
