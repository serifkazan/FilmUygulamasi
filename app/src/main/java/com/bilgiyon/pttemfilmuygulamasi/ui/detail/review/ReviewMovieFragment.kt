package com.bilgiyon.pttemfilmuygulamasi.ui.detail.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bilgiyon.pttemfilmuygulamasi.R
import com.bilgiyon.pttemfilmuygulamasi.common.BaseVMFragment
import com.bilgiyon.pttemfilmuygulamasi.model.movie.MovieResults
import com.bilgiyon.pttemfilmuygulamasi.util.gone
import com.bilgiyon.pttemfilmuygulamasi.util.visible
import kotlinx.android.synthetic.main.fragment_reviews.*

class ReviewMovieFragment : BaseVMFragment<ReviewViewModel>() {
    lateinit var adapter: ReviewAdapter

    override fun getViewModel(): Class<ReviewViewModel> = ReviewViewModel::class.java

    companion object {
        private const val MOVIE_KEY = "movie_overview_key"
        fun newInstance(movie: MovieResults?): ReviewMovieFragment {
            val args = Bundle()
            args.putParcelable(MOVIE_KEY, movie)

            val fragment = ReviewMovieFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reviews, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movie: MovieResults = arguments?.getParcelable<MovieResults>(MOVIE_KEY) as MovieResults
        val movieId: Int = movie.movieId
        adapter = ReviewAdapter()
        review_recyclerview.layoutManager = LinearLayoutManager(activity)
        viewModel.getReviews(movieId)?.observe(this, Observer {
            review_recyclerview.adapter = adapter
            adapter.submitList(it)

            review_progress.gone()
            review_recyclerview.visible()
        })
    }
}