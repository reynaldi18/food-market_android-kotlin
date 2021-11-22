package com.movieApp.app.ui.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.movieApp.app.R
import com.movieApp.app.api.Resource
import com.movieApp.app.core.CoreListAdapter
import com.movieApp.app.core.CoreVm
import com.movieApp.app.data.*
import com.movieApp.app.helper.SingleLiveEvent
import com.movieApp.app.repo.MovieRepo
import kotlinx.coroutines.launch

internal class MovieDetailVm(
    private val repo: MovieRepo
) : CoreVm() {
    val state = ObservableField(STATE_HAS_DATA)
    val stateReviews = ObservableField(STATE_HAS_DATA)
    val stateVideos = ObservableField(STATE_HAS_DATA)
    private var page: Int = 0
    var movie = ObservableField<Movie>()
    private var reviews = ObservableField<Review>()
    private var videos = ObservableField<Videos>()
    private var listReview = arrayListOf<Author>()
    private var listVideo = arrayListOf<Video>()
    private var listGenre = arrayListOf<Genre>()
    var reviewAdapter: CoreListAdapter = CoreListAdapter(R.layout.item_review)
    var videoAdapter: CoreListAdapter = CoreListAdapter(R.layout.item_video)
    var genreAdapter: CoreListAdapter = CoreListAdapter(R.layout.item_genre)
    var showVideo = SingleLiveEvent<Video>()

    init {
        videoAdapter.onItemClick = { _, position ->
            showVideo.value = listVideo[position]
        }
    }

    fun getMovieDetail(id: Int?) = viewModelScope.launch {
        state.set(STATE_PROGRESS)
        when (val res = repo.fetchDetail(id)) {
            is Resource.Error -> {
                state.set(STATE_FAILED)
                onFailed.value = res.message
            }
            is Resource.Success -> {
                state.set(STATE_HAS_DATA)
                movie.set(res.data)
                setGenre(res.data?.genres)
            }
        }
    }

    fun getReviews(id: Int?, refresh: Boolean = false) = viewModelScope.launch {
        if (!refresh)
            page++
        else {
            page = 1
            listReview.clear()
        }
        stateReviews.set(STATE_PROGRESS)
        when (val res = repo.fetchReviews(id, page)) {
            is Resource.Error -> {
                stateReviews.set(STATE_FAILED)
                onFailed.value = res.message
            }
            is Resource.Success -> {
                stateReviews.set(STATE_HAS_DATA)
                reviews.set(res.data)
                setReviews(res.data?.results, refresh)
            }
        }
    }

    fun getVideos(id: Int?) = viewModelScope.launch {
        stateVideos.set(STATE_PROGRESS)
        when (val res = repo.fetchVideos(id)) {
            is Resource.Error -> {
                stateVideos.set(STATE_FAILED)
                onFailed.value = res.message
            }
            is Resource.Success -> {
                stateVideos.set(STATE_HAS_DATA)
                videos.set(res.data)
                setVideos(res.data?.results)
            }
        }
    }

    private fun setGenre(list: List<Genre>?) {
        this.listGenre.clear()
        list?.let { this.listGenre.addAll(it) }
        state.set(STATE_PROGRESS)
        state.set(if (this.listGenre.isEmpty()) STATE_NO_DATA else STATE_HAS_DATA)
        genreAdapter.items = this.listGenre
    }

    private fun setReviews(list: List<Author>?, refresh: Boolean) {
        if (refresh) this.listReview.clear()
        list?.let { this.listReview.addAll(it) }
        stateReviews.set(STATE_PROGRESS)
        stateReviews.set(if (this.listReview.isEmpty()) STATE_NO_DATA else STATE_HAS_DATA)
        reviewAdapter.items = this.listReview
        reviewAdapter.notifyDataSetChanged()
    }

    private fun setVideos(list: List<Video>?) {
        this.listVideo.clear()
        list?.let { this.listVideo.addAll(it) }
        stateVideos.set(STATE_PROGRESS)
        stateVideos.set(if (this.listVideo.isEmpty()) STATE_NO_DATA else STATE_HAS_DATA)
        videoAdapter.items = this.listVideo
    }
}
