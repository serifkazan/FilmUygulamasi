package com.bilgiyon.pttemfilmuygulamasi.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelper(
    val context: Context
) :
    SQLiteOpenHelper(context, SqliteHelper.DATABASE_NAME, null, SqliteHelper.DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "PttemMovieDb"
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        createFavoritesTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun createFavoritesTable(db: SQLiteDatabase?) {
        val query = "CREATE TABLE IF NOT EXISTS FavoriteMovies(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "MovieId TEXT," +
                "OriginalMovieName TEXT," +
                "MovieName TEXT," +
                "PosterPath TEXT)"
        db?.execSQL(query)
    }


    fun insert(tableName: String, contentValues: ContentValues) {
        val db: SQLiteDatabase = writableDatabase
        val result = db.insert(tableName, null, contentValues)
    }

    fun delete(tableName: String, whereQuery: String, whereParams: Array<String>) {
        val db: SQLiteDatabase = writableDatabase
        val result = db.delete(tableName, whereQuery, whereParams)
    }

    fun executeRawQuery(query: String): Cursor {
        val db: SQLiteDatabase = writableDatabase
        return db.rawQuery(query, null)
    }
}