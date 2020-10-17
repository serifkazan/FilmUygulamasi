package com.bilgiyon.pttemfilmuygulamasi.ui.main.favorites

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
import com.bilgiyon.pttemfilmuygulamasi.ui.detail.DetailViewModel
import com.bilgiyon.pttemfilmuygulamasi.ui.main.MovieAdapter
import com.bilgiyon.pttemfilmuygulamasi.util.Constants
import com.bilgiyon.pttemfilmuygulamasi.util.gone
import com.bilgiyon.pttemfilmuygulamasi.util.visible
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : BaseVMFragment<DetailViewModel>(), MovieAdapter.OnMovieClickListener {
    private lateinit var adapter: MovieAdapter

    override fun getViewModel(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = MovieAdapter(this)
        favorites_recyclerview.layoutManager = GridLayoutManager(activity, 2)
        viewModel.getAllMovies().observe(this, Observer {
            adapter.submitList(it)
            favorites_recyclerview.adapter = adapter
            favorites_recyclerview.visible()
            favorites_progressbar.gone()
        })
    }

    override fun onMovieClick(movieResults: MovieResults?) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(Constants.EXTRA_POPULAR_MOVIES, movieResults)
        startActivity(intent)
    }
}