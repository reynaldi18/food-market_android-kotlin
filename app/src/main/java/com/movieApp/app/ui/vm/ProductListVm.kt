package com.movieApp.app.ui.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.movieApp.app.R
import com.movieApp.app.api.Resource
import com.movieApp.app.core.CoreListAdapter
import com.movieApp.app.core.CoreVm
import com.movieApp.app.data.Product
import com.movieApp.app.helper.SingleLiveEvent
import com.movieApp.app.repo.ProductRepo
import kotlinx.coroutines.launch

internal class ProductListVm(
    private val repo: ProductRepo,
) : CoreVm() {
    val stateList = ObservableField(STATE_NO_DATA)
    var listProduct = mutableListOf<Product>()
    val productAdapter = CoreListAdapter(R.layout.item_product)
    var showProduct = SingleLiveEvent<Product>()
    var type = ObservableField("")
    val product = ObservableField<Product>()

    init {
        productAdapter.onItemClick = {_, position ->
            showProduct.value = listProduct[position]
        }
    }

    fun getProducts(status: String) = viewModelScope.launch {
        stateList.set(STATE_PROGRESS)
        val res = when (status) {
            Product.STATUS_NEW -> {
                repo.fetchNewProducts()
            }
            Product.STATUS_POPULAR -> {
                repo.fetchPopularProducts()
            }
            else -> {
                repo.fetchRecommendedProducts()
            }
        }
        when (res) {
            is Resource.Error -> {
                stateList.set(STATE_FAILED)
                onFailed.value = res.message
            }
            is Resource.Success -> res.data?.let { listProduct(it) }
        }
    }

    private fun listProduct(list: List<Product>) {
        listProduct.clear()
        list.let { listProduct.addAll(it) }
        productAdapter.items = listProduct
        stateList.set(if (!listProduct.isNullOrEmpty()) STATE_HAS_DATA else STATE_NO_DATA)
        productAdapter.notifyDataSetChanged()
        onSuccess.call()
    }
}
