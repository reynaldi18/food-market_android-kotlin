package com.fMarket.app.ui.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.fMarket.app.R
import com.fMarket.app.api.Resource
import com.fMarket.app.core.CoreListAdapter
import com.fMarket.app.core.CoreVm
import com.fMarket.app.data.Product
import com.fMarket.app.helper.getString
import com.fMarket.app.repo.ProductRepo
import com.fMarket.app.repo.UserRepo
import kotlinx.coroutines.launch

internal class HomeVm(
    private val repo: UserRepo,
    private val repoProduct: ProductRepo,
) : CoreVm() {
    var user = repo.getUser()
    var currentItem: Int = 0
    val state = ObservableField(STATE_HAS_DATA)
    var list = arrayListOf<Product>()
    val listMessage = ObservableField("")
    var highlightAdapter: CoreListAdapter = CoreListAdapter(R.layout.item_highlight)
    val noData = getString(R.string.message_no_data)

    init {
        fetchHighlight()
    }

    private fun fetchHighlight() = viewModelScope.launch {
        state.set(if (list.isEmpty()) STATE_PROGRESS else STATE_HAS_DATA)
        val res = repoProduct.fetchHighlight()
        res.message?.let { listMessage.set(res.message) }
        when (res) {
            is Resource.Error -> state.set(STATE_FAILED)
            is Resource.Success -> setHighlight(res.data)
        }
    }

    private fun setHighlight(list: List<Product>?) {
        this.list.clear()
        list?.let { this.list.addAll(it) }
        state.set(STATE_PROGRESS)
        state.set(if (this.list.isEmpty()) STATE_NO_DATA else STATE_HAS_DATA)
        highlightAdapter.items = this.list
    }
}
