package com.bilgiyon.pttemfilmuygulamasi.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bilgiyon.pttemfilmuygulamasi.databinding.MovieItemBinding
import com.bilgiyon.pttemfilmuygulamasi.model.movie.MovieResults

class MovieAdapter(onMovieClickListener: OnMovieClickListener) :
    ListAdapter<MovieResults, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {
    var mOnMovieClickListener: OnMovieClickListener = onMovieClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(LayoutInflater.from(parent.context), parent, mOnMovieClickListener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ViewHolder(val binding: MovieItemBinding, onMovieClickListener: OnMovieClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onMovieClickListener.onMovieClick(binding.movie!!)
            }
        }

        companion object {
            fun create(
                inflater: LayoutInflater,
                parent: ViewGroup,
                onMovieClickListener: OnMovieClickListener
            ): ViewHolder {
                val itemMovieBinding = MovieItemBinding.inflate(inflater, parent, false)
                return ViewHolder(itemMovieBinding, onMovieClickListener)
            }
        }

        fun bind(movieResults: MovieResults) {
            binding.movie = movieResults
            binding.executePendingBindings()
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieResults>() {
            override fun areItemsTheSame(oldItem: MovieResults, newItem: MovieResults): Boolean =
                oldItem.movieId == newItem.movieId

            override fun areContentsTheSame(oldItem: MovieResults, newItem: MovieResults): Boolean =
                oldItem.title == newItem.title

        }
    }

    interface OnMovieClickListener {
        fun onMovieClick(movieResults: MovieResults?)
    }
}