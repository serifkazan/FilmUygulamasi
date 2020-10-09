package com.bilgiyon.pttemfilmuygulamasi.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.bilgiyon.pttemfilmuygulamasi.BuildConfig
import com.bilgiyon.pttemfilmuygulamasi.api.ApiClient
import com.bilgiyon.pttemfilmuygulamasi.api.ApiCommand
import com.bilgiyon.pttemfilmuygulamasi.api.ApiService
import com.bilgiyon.pttemfilmuygulamasi.db.SqliteHelper
import retrofit2.Call
import retrofit2.Response

private const val API_KEY = BuildConfig.TMDB_API_KEY

class MovieRepository(
    val context: Context,
    val sqliteHelper: SqliteHelper = SqliteHelper(context)
) {
    fun insertFavoriteMovie(movieModel: MovieModel?) {
        val posterPath: String
        if (movieModel?.poster_path != null && !movieModel?.poster_path?.isEmpty())
            posterPath = movieModel?.poster_path
        else
            posterPath = movieModel?.backdrop_path.toString()
        val contentValues = ContentValues()
        contentValues.put("MovieId", movieModel?.id)
        contentValues.put("OriginalMovieName", movieModel?.original_title)
        contentValues.put("MovieName", movieModel?.title)
        contentValues.put("PosterPath", posterPath)

        sqliteHelper.insert("FavoriteMovies", contentValues)
    }

    fun deleteFavoriteMovie(movieModel: MovieModel?) {
        val whereQuery: String = "MovieId = ?"
        val whereParams: MutableList<String> = ArrayList()
        whereParams.add(movieModel?.id.toString())
        sqliteHelper.delete("FavoriteMovies", whereQuery, whereParams.toTypedArray())
    }

    fun getFavoriteMovies(): MutableList<MovieModel> {
        val movieList: MutableList<MovieModel> = ArrayList()
        val query: String = "Select * From FavoriteMovies"
        val cursor: Cursor = sqliteHelper.executeRawQuery(query)

        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {
                val movieId: String = cursor.getString(cursor.getColumnIndex("MovieId"))
                val originalMovieName: String =
                    cursor.getString(cursor.getColumnIndex("OriginalMovieName"))
                val movieName: String = cursor.getString(cursor.getColumnIndex("MovieName"))
                val posterPath: String = cursor.getString(cursor.getColumnIndex("PosterPath"))

                val movieModel: MovieModel = MovieModel(
                    false, "", null, movieId.toInt(), "", originalMovieName, "",
                    0.0, posterPath, "", movieName, false, 0.0, 0
                )

                movieList.add(movieModel)
            }
        }

        return movieList
    }

    fun getFavoriteMovie(movieId: Int?): MovieModel? {
        var movieModel: MovieModel? = null
        val query = "Select * from FavoriteMovies Where MovieId='" + movieId + "'"
        val cursor = sqliteHelper.executeRawQuery(query)
        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {
                val movieId: String = cursor.getString(cursor.getColumnIndex("MovieId"))
                val originalMovieName: String =
                    cursor.getString(cursor.getColumnIndex("OriginalMovieName"))
                val movieName: String = cursor.getString(cursor.getColumnIndex("MovieName"))
                val posterPath: String = cursor.getString(cursor.getColumnIndex("PosterPath"))

                movieModel = MovieModel(
                    false, "", null, movieId.toInt(), "", originalMovieName, "",
                    0.0, posterPath, "", movieName, false, 0.0, 0
                )
            }
        }

        return movieModel
    }

    fun getMovies(pageNumber: Int, apiCommand: ApiCommand<List<MovieModel>?>) {
        ApiClient.getClient().create(ApiService::class.java)
            .discoverMovies(pageNumber, "popularity.asc", API_KEY)
            .enqueue(object : retrofit2.Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        val result: MovieResponse? = response.body()
                        val movieList = result?.results
                        apiCommand.onSuccess(movieList)
                    } else {
                        apiCommand.onFailure("İşlem yapılırken bir hata oluştu!")
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    apiCommand.onFailure(t.localizedMessage)
                }
            })
    }

    fun getConfigurations(apiCommand: ApiCommand<ConfigurationModel?>) {
        ApiClient.getClient().create(ApiService::class.java)
            .getConfigurations(API_KEY)
            .enqueue(object : retrofit2.Callback<ConfigurationModel> {
                override fun onFailure(call: Call<ConfigurationModel>, t: Throwable) {
                    apiCommand.onFailure(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ConfigurationModel>,
                    response: Response<ConfigurationModel>
                ) {
                    if (response.isSuccessful) {
                        val result: ConfigurationModel? = response.body()
                        apiCommand.onSuccess(result)
                    } else {
                        apiCommand.onFailure("İşlem yapılırken bir hata oluştu!")
                    }
                }

            })
    }
}