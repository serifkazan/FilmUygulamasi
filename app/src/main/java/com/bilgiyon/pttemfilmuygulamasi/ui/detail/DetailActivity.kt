package com.bilgiyon.pttemfilmuygulamasi.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bilgiyon.pttemfilmuygulamasi.MainActivity
import com.bilgiyon.pttemfilmuygulamasi.R
import com.bilgiyon.pttemfilmuygulamasi.data.MovieModel
import com.bilgiyon.pttemfilmuygulamasi.data.MovieRepository
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    lateinit var movieRepository: MovieRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        movieRepository = MovieRepository(this)
        val movieModel = intent.getSerializableExtra("selectedmovie") as? MovieModel


        toolbar.title = movieModel?.title

        releasedate.text = movieModel?.release_date
        originalmoviename.text = movieModel?.original_title
        overview.text = movieModel?.overview

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
            Glide.with(this).load(posterPath)
                .thumbnail(Glide.with(this).load(fullPosterPath))
                .apply(RequestOptions.centerCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade()).into(imageview)
        }

        btnfav.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                addRemoveFavorites(movieModel)
            }

        })
    }

    fun addRemoveFavorites(movieModel: MovieModel?) {
        val selectedMovie = movieRepository.getFavoriteMovie(movieModel?.id)
        if (selectedMovie == null) {
            movieRepository.insertFavoriteMovie(movieModel)
            Toast.makeText(this,"Favorilere Eklendi",Toast.LENGTH_LONG).show()
        } else {
            movieRepository.deleteFavoriteMovie(movieModel)
            Toast.makeText(this,"Favorilerden Silindi",Toast.LENGTH_LONG).show()
        }
    }
}