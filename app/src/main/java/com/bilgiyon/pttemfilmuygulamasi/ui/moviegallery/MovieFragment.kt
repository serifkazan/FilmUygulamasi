package com.bilgiyon.pttemfilmuygulamasi.ui.moviegallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bilgiyon.pttemfilmuygulamasi.R
import com.bilgiyon.pttemfilmuygulamasi.api.ApiCommand
import com.bilgiyon.pttemfilmuygulamasi.data.MovieModel
import com.bilgiyon.pttemfilmuygulamasi.data.MovieRepository
import com.bilgiyon.pttemfilmuygulamasi.ui.EndlessScrollListener
import com.bilgiyon.pttemfilmuygulamasi.ui.ScrollToBottomListener
import com.bilgiyon.pttemfilmuygulamasi.ui.adapter.MovieAdapter
import com.bilgiyon.pttemfilmuygulamasi.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.coroutines.Dispatchers.Main

const val STARTING_PAGE = 1

class MovieFragment : Fragment() {
    lateinit var movieRepository: MovieRepository
    var adapter: MovieAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieRepository = MovieRepository(activity as Context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_movie, container, false)
        val recycler_view = view.findViewById(R.id.recycler_view) as RecyclerView
        val layoutManager = LinearLayoutManager(activity)
        var endlessScrollListener = EndlessScrollListener(
            layoutManager,
            object : ScrollToBottomListener {
                override fun onScrollToBottom(nextPageNumber: Int) {
                    getRemoteMovies(nextPageNumber, false)
                }
            }, STARTING_PAGE
        )
        recycler_view.layoutManager = layoutManager
        recycler_view.addOnScrollListener(endlessScrollListener)
        getRemoteMovies(STARTING_PAGE, true)
        return view
    }

    fun getRemoteMovies(pageNumber: Int, isRefresh: Boolean) {
        val apiCommand = object : ApiCommand<List<MovieModel>?> {
            override fun onSuccess(response: List<MovieModel>?) {
                val movieList = response as MutableList<MovieModel>
                if (adapter == null) {
                    adapter = MovieAdapter(movieList, object : MovieAdapter.OnMovieClickListener {
                        override fun onMovieClick(movie: MovieModel?) {
                            val intent = Intent(activity, DetailActivity::class.java)
                            intent.putExtra("selectedmovie", movie)
                            startActivity(intent)
                        }

                    })
                    recycler_view.adapter = adapter
                } else {
                    if (isRefresh) {
                        adapter?.clear()
                    }
                    adapter?.addAll(movieList)
                    adapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(t: String) {
                Toast.makeText(activity, t, Toast.LENGTH_LONG).show()
            }

        }
        movieRepository.getMovies(if (isRefresh) STARTING_PAGE else pageNumber, apiCommand)
    }
}