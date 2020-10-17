package com.bilgiyon.pttemfilmuygulamasi.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bilgiyon.pttemfilmuygulamasi.model.movie.MovieResults

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertMovie(movie: MovieResults?)

    @Delete
    fun DeleteMovie(movie: MovieResults?)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MovieResults>>

    @Query("SELECT * FROM movies WHERE movieId= :movieId")
    fun getSingleMovie(movieId: Int?): LiveData<MovieResults>

}