package com.movieApp.app.ui.fragment

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.movieApp.app.core.CoreFragment
import com.movieApp.app.databinding.FragmentMovieDetailBinding
import com.movieApp.app.listener.EndlessOnScrollListener
import com.movieApp.app.ui.vm.MovieDetailVm
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class MovieDetailFragment : CoreFragment() {
    private lateinit var bind: FragmentMovieDetailBinding
    private val vm: MovieDetailVm by viewModel()
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        vm.getMovieDetail(args.id)
        vm.getReviews(args.id)
        vm.getVideos(args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentMovieDetailBinding.inflate(inflater, container, false)
        setupToolbar(bind.toolbar)
        bind.vm = vm
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val nav = view.findNavController()
        vm.showProgress.observe(viewLifecycleOwner, { if (it) showProgress() else hideProgress() })
        vm.showVideo.observe(
            viewLifecycleOwner,
            {
                it.key?.let { it1 -> watchYoutubeVideo(it1) }
            }
        )
        bind.rvMain.addOnScrollListener(object : EndlessOnScrollListener() {
            override fun onLoadMore() {
                vm.getReviews(args.id)
            }
        })
    }

    private fun watchYoutubeVideo(key: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$key"))
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://www.youtube.com/watch?v=$key")
        )
        try {
            startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            startActivity(webIntent)
        }
    }
}
