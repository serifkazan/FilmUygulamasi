package com.bilgiyon.pttemfilmuygulamasi.ui.main.toprated


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bilgiyon.pttemfilmuygulamasi.R
import com.bilgiyon.pttemfilmuygulamasi.common.BaseVMFragment
import com.bilgiyon.pttemfilmuygulamasi.model.movie.MovieResults
import com.bilgiyon.pttemfilmuygulamasi.ui.detail.DetailActivity
import com.bilgiyon.pttemfilmuygulamasi.ui.main.MovieAdapter
import com.bilgiyon.pttemfilmuygulamasi.util.Constants
import com.bilgiyon.pttemfilmuygulamasi.util.gone
import com.bilgiyon.pttemfilmuygulamasi.util.visible
import kotlinx.android.synthetic.main.fragment_popular_movies.*

class TopRatedFragment : BaseVMFragment<TopRatedViewModel>(), MovieAdapter.OnMovieClickListener {
    lateinit var adapter: MovieAdapter

    override fun getViewModel(): Class<TopRatedViewModel> {
        return TopRatedViewModel::class.java
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = MovieAdapter(this)
        popular_recyclerview.layoutManager = GridLayoutManager(activity, 2)
        viewModel.getTopRatedMovies()?.observe(this, Observer {
            adapter.submitList(it)
            popular_recyclerview.adapter = adapter
            popular_recyclerview.visible()
            popular_progressbar.gone()
        })
    }

    override fun onMovieClick(movieResults: MovieResults?) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(Constants.EXTRA_POPULAR_MOVIES, movieResults)
        startActivity(intent)
    }
}