package com.bilgiyon.pttemfilmuygulamasi.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bilgiyon.pttemfilmuygulamasi.data.MovieModel
import com.bilgiyon.pttemfilmuygulamasi.ui.viewholder.MovieViewHolder

class MovieAdapter(movieList: MutableList<MovieModel>?, movieClickListener: OnMovieClickListener) :
    RecyclerView.Adapter<MovieViewHolder>() {
    var movieList = movieList
    var listener = movieClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return if (movieList == null) 0 else movieList?.size!!
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(movieList?.get(position), listener)
    }

    fun clear() {
        movieList?.clear()
    }

    fun addAll(movies: MutableList<MovieModel>?) {
        movieList?.addAll(movies as Iterable<MovieModel>)
    }

    public interface OnMovieClickListener {
        fun onMovieClick(movie: MovieModel?)
    }
}