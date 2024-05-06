package com.example.mymovielist

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ListMovieOpenHelper(context: Context) : SQLiteOpenHelper(context, ListMovieOpenHelper.NOME_BANCO_DE_DADOS, null, ListMovieOpenHelper.VERSAO_BANCO_DE_DADOS) {
    companion object {
        private const val NOME_BANCO_DE_DADOS = "UserMovieList.db"
        private const val VERSAO_BANCO_DE_DADOS = 2
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sql = "CREATE TABLE UserMovieList (" +
                "UserId INTEGER, " +
                "Id INTEGER, " +
                "BackdropPath TEXT, " +
                "OriginalLanguage TEXT, " +
                "Overview TEXT, " +
                "ReleaseDate TEXT, " +
                "Title TEXT, " +
                "VoteAverage TEXT)"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Seu código de migração do banco de dados aqui
        if (newVersion > oldVersion) {
            // Crie uma nova tabela temporária sem a coluna "age"
            db.execSQL("CREATE TABLE movie_temp (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "email TEXT NOT NULL UNIQUE, " +
                    "password TEXT NOT NULL)")

            // Copie os dados da tabela antiga para a nova tabela
            db.execSQL("INSERT INTO movie_temp (name, email, password) SELECT name, email, password FROM UserMovieList")

            // Exclua a tabela antiga
            db.execSQL("DROP TABLE UserMovieList")

            // Renomeie a nova tabela para o nome original
            db.execSQL("ALTER TABLE movie_temp RENAME TO user")
        }
    }

    fun insertMovie(userId: String, filme: Filme){
        val db = writableDatabase
        val values = ContentValues().apply {
            put("Id", filme.id)
            put("UserId", userId)
            put("BackdropPath", filme.backdrop_path)
            put("Overview", filme.overview)
            put("ReleaseDate", filme.release_date)
            put("Title", filme.title)
            put("VoteAverage", filme.vote_average)
        }

        db.insert("UserMovieList", null, values)
        db.close()
    }

    fun getAllMoviesById(userId: String): List<Filme> {
        val movies = mutableListOf<Filme>()
        val db = readableDatabase

        val selection = "UserId = ?"
        val selectionArgs = arrayOf(userId.toString())

        val cursor = db.query(
            "UserMovieList",
            null, // Retorna todas as colunas
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            val movie = Filme(
                id = cursor.getString(cursor.getColumnIndexOrThrow("Id")),
                overview = cursor.getString(cursor.getColumnIndexOrThrow("Overview")),
                backdrop_path = cursor.getString(cursor.getColumnIndexOrThrow("BackdropPath")),
                release_date = cursor.getString(cursor.getColumnIndexOrThrow("ReleaseDate")),
                title = cursor.getString(cursor.getColumnIndexOrThrow("Title")),
                vote_average = cursor.getString(cursor.getColumnIndexOrThrow("VoteAverage"))
            )
            movies.add(movie)
        }

        cursor.close()

        return movies
    }

    fun deleteMovieById(movieId: Int): Int {
        val db = this.writableDatabase
        return db.delete("UserMovieList", "Id = ?", arrayOf(movieId.toString()))
    }
}