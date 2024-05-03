package com.example.mymovielist

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UsuarioOpenHelper(context: Context) : SQLiteOpenHelper(context, NOME_BANCO_DE_DADOS, null, VERSAO_BANCO_DE_DADOS) {

    companion object {
        private const val NOME_BANCO_DE_DADOS = "usuario.db"
        private const val VERSAO_BANCO_DE_DADOS = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sql = "CREATE TABLE user (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "email TEXT NOT NULL UNIQUE, " +
                "password TEXT NOT NULL, " +
                "age INTEGER NOT NULL)"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    fun insertUser(user: User){
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", user.name)
            put("email", user.email)
            put("password", user.password)
            put("age", user.age)
        }
            db.insert("user", null, values)
            db.close()
    }

    fun verificarUsuario(email: String, password: String): Boolean {
        val db = readableDatabase
        val colunas = arrayOf("id")
        val selecao = "email = ? AND password = ?"
        val selecaoArgs = arrayOf(email, password)
        val cursor: Cursor = db.query("user", colunas, selecao, selecaoArgs, null, null, null)
        val usuarioExiste = cursor.count > 0
        cursor.close()
        return usuarioExiste
    }
}