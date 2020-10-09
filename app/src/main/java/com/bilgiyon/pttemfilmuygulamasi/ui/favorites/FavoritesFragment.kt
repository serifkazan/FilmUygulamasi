package com.bilgiyon.pttemfilmuygulamasi.ui.favorites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bilgiyon.pttemfilmuygulamasi.R
import com.bilgiyon.pttemfilmuygulamasi.data.MovieModel
import com.bilgiyon.pttemfilmuygulamasi.data.MovieRepository
import com.bilgiyon.pttemfilmuygulamasi.ui.adapter.MovieAdapter
import com.bilgiyon.pttemfilmuygulamasi.ui.detail.DetailActivity

class FavoritesFragment : Fragment() {
    lateinit var movieRepository: MovieRepository
    var adapter: MovieAdapter? = null
    lateinit var recycler_view: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieRepository = MovieRepository(activity as Context)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            LoadView()
        }
    }

    override fun onResume() {
        super.onResume()
        LoadView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_favorites, container, false)
        recycler_view = view.findViewById(R.id.recycler_view) as RecyclerView
        LoadView()
        return view
    }

    fun LoadView() {
        val movieList: MutableList<MovieModel> = movieRepository.getFavoriteMovies()
        val layoutManager = LinearLayoutManager(activity)
        recycler_view.layoutManager = layoutManager
        adapter = MovieAdapter(movieList, object : MovieAdapter.OnMovieClickListener {
            override fun onMovieClick(movie: MovieModel?) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra("selectedmovie", movie)
                startActivity(intent)
            }

        })
        recycler_view.adapter = adapter
    }
}