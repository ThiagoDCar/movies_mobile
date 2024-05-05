package com.example.mymovielist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mymovielist.databinding.ActivityMovieSearchBinding
import okhttp3.OkHttpClient
import okhttp3.Request

class MovieSearch : AppCompatActivity() {

    private lateinit var view: ActivityMovieSearchBinding
    private lateinit var db: UsuarioOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        view = ActivityMovieSearchBinding.inflate(layoutInflater)
        setContentView(view.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = UsuarioOpenHelper(this)

        view.button.setOnClickListener {
            //val pesquisa = view.pesquisar.text.toString()
            //val API_KEY = "b2ca12b72a2d39fd17fa0c3fcfc37ae7"

            //val a = "https://api.themoviedb.org/3/search/movie?query=$pesquisa&api_key=$API_KEY"

            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://api.themoviedb.org/3/authentication")
                .get()
                .addHeader("Authorization", "b2ca12b72a2d39fd17fa0c3fcfc37ae7")
                .build()

            val response = client.newCall(request).execute()

            Toast.makeText(this, "Feito!!", Toast.LENGTH_SHORT).show()
        }
    }
}