package com.movieApp.app.ui.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.movieApp.app.R
import com.movieApp.app.api.Resource
import com.movieApp.app.core.CoreListAdapter
import com.movieApp.app.core.CoreVm
import com.movieApp.app.data.Movie
import com.movieApp.app.helper.SingleLiveEvent
import com.movieApp.app.helper.getString
import com.movieApp.app.repo.MovieRepo
import kotlinx.coroutines.launch

internal class HomeVm(
    private val repo: MovieRepo
) : CoreVm() {
    val state = ObservableField(STATE_HAS_DATA)
    var list = arrayListOf<Movie>()
    val listMessage = ObservableField("")
    var movieAdapter: CoreListAdapter = CoreListAdapter(R.layout.item_movie)
    val noData = getString(R.string.message_no_data)
    var showMovie = SingleLiveEvent<Movie>()
    private var page: Int = 0

    init {
        movieAdapter.onItemClick = { _, position ->
            showMovie.value = list[position]
        }
        fetchDiscover()
    }

    fun fetchDiscover(refresh: Boolean = false) = viewModelScope.launch {
        if (!refresh)
            page++
        else {
            page = 1
            list.clear()
        }
        state.set(if (list.isEmpty()) STATE_PROGRESS else STATE_HAS_DATA)
        val res = repo.fetchDiscover(page)
        res.message?.let { listMessage.set(res.message) }
        when (res) {
            is Resource.Error -> state.set(STATE_FAILED)
            is Resource.Success -> setMovies(res.data, refresh)
        }
    }

    private fun setMovies(list: List<Movie>?, refresh: Boolean) {
        if (refresh) this.list.clear()
        list?.let { this.list.addAll(it) }
        state.set(STATE_PROGRESS)
        state.set(if (this.list.isEmpty()) STATE_NO_DATA else STATE_HAS_DATA)
        movieAdapter.items = this.list
        movieAdapter.notifyDataSetChanged()
    }
}
