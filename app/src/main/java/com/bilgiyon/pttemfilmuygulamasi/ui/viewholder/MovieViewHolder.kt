package com.bilgiyon.pttemfilmuygulamasi.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bilgiyon.pttemfilmuygulamasi.MainActivity
import com.bilgiyon.pttemfilmuygulamasi.R
import com.bilgiyon.pttemfilmuygulamasi.data.MovieModel
import com.bilgiyon.pttemfilmuygulamasi.ui.adapter.MovieAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions

class MovieViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(viewGroup.context).inflate(R.layout.movie_item, viewGroup, false)
) {
    private val txtMovieName by lazy { itemView.findViewById<TextView>(R.id.moviename) }
    private val txtOriginalMovieName by lazy { itemView.findViewById<TextView>(R.id.originalmoviename) }
    private val imgMoviePoster by lazy { itemView.findViewById<ImageView>(R.id.movieimage) }
    private val container by lazy { itemView.findViewById<CardView>(R.id.container) }

    fun bindTo(movieModel: MovieModel?, movieClickListener: MovieAdapter.OnMovieClickListener) {
        txtMovieName.text = movieModel?.title
        txtOriginalMovieName.text = movieModel?.original_title

        val posterPath: String?
        var fullPosterPath: String? = null
        if (movieModel?.poster_path != null && !movieModel?.poster_path.isEmpty())
            posterPath = movieModel.poster_path
        else
            posterPath = movieModel?.backdrop_path

        if (MainActivity.configuration != null && MainActivity.configuration!!.images != null &&
            MainActivity.configuration!!.images.base_url != null && !MainActivity.configuration!!.images.base_url.isEmpty()
        ) {
            if (MainActivity.configuration!!.images.poster_sizes != null) {
                if (MainActivity.configuration!!.images.poster_sizes.size > 4) {
                    fullPosterPath =
                        MainActivity.configuration!!.images.base_url + MainActivity.configuration!!.images.poster_sizes.get(
                            4
                        ) + posterPath
                } else {
                    fullPosterPath =
                        MainActivity.configuration!!.images.base_url + "w500" + posterPath
                }
            }
        }

        if (fullPosterPath != null) {
            Glide.with(itemView.context).load(posterPath)
                .thumbnail(Glide.with(itemView.context).load(fullPosterPath))
                .apply(RequestOptions.centerCropTransform())
                .transition(withCrossFade()).into(imgMoviePoster)
        }

        val clickListener = View.OnClickListener { view ->
            movieClickListener.onMovieClick(movieModel)
        }

        container.setOnClickListener(clickListener)
    }
}