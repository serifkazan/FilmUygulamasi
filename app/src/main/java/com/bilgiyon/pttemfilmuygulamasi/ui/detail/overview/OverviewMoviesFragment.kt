package com.bilgiyon.pttemfilmuygulamasi.ui.detail.overview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bilgiyon.pttemfilmuygulamasi.R
import com.bilgiyon.pttemfilmuygulamasi.common.BaseFragment
import com.bilgiyon.pttemfilmuygulamasi.databinding.FragmentOverviewBinding
import com.bilgiyon.pttemfilmuygulamasi.model.movie.MovieResults
import com.bilgiyon.pttemfilmuygulamasi.model.videos.MovieVideoResults
import com.bilgiyon.pttemfilmuygulamasi.util.Constants
import com.bilgiyon.pttemfilmuygulamasi.util.gone
import com.bilgiyon.pttemfilmuygulamasi.util.visible
import kotlinx.android.synthetic.main.fragment_overview.*

class OverviewMoviesFragment : BaseFragment<FragmentOverviewBinding, OverviewMoviesViewModel>(),
    VideoAdapter.OnVideoClickListener {
    private lateinit var videoAdapter: VideoAdapter

    override fun getLayoutRes(): Int = R.layout.fragment_overview

    override fun getViewModel(): Class<OverviewMoviesViewModel> =
        OverviewMoviesViewModel::class.java

    companion object {
        private const val MOVIE_KEY = "movie_overview_key"
        fun newInstance(movie: MovieResults?): OverviewMoviesFragment {
            val args = Bundle()
            args.putParcelable(MOVIE_KEY, movie)

            val fragment = OverviewMoviesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val movie: MovieResults = arguments?.getParcelable<MovieResults>(MOVIE_KEY) as MovieResults
        val movieId: Int = movie.movieId

        viewModel.getDetails(movieId).observe(this, Observer {
            dataBinding.details = it
        })

        detail_movie_videos_progress.visible()
        movie_videos_recyclerview.gone()

        viewModel.getVideos(movieId).observe(this, Observer {
            videoAdapter = VideoAdapter(this)
            movie_videos_recyclerview.adapter = videoAdapter
            videoAdapter.submitList(it)

            detail_movie_videos_progress.gone()
            movie_videos_recyclerview.visible()
        })
    }

    override fun onVideoClick(video: MovieVideoResults) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(Constants.YOUTUBE_WATCH_URL + video.key)
        startActivity(intent)
    }
}