package com.example.mymovielist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mymovielist.databinding.ActivityFormLoginBinding
import com.example.mymovielist.databinding.ActivityMovieSearchBinding

class MovieSearch : AppCompatActivity() {

    private lateinit var view: ActivityFormLoginBinding
    private lateinit var db: UsuarioOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        view = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(view.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = UsuarioOpenHelper(this)

        view.button.setOnClickListener {
            val pesquisa = view.pesquisar.text.toString()

            val a = "https://api.themoviedb.org/3/search/movie?query=" + pesquisa + "&api_key=b2ca12b72a2d39fd17fa0c3fcfc37ae7"

            Toast.makeText(this, a, Toast.LENGTH_SHORT).show()
        }
    }
}