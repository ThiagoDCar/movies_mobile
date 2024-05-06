package com.example.mymovielist


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovielist.databinding.ActivityListaFilmesBinding

class ListaFilmes : AppCompatActivity() {

    private lateinit var view: ActivityListaFilmesBinding
    private lateinit var db: ListMovieOpenHelper
    private lateinit var FilmesListAdapter: FilmesListAdapter
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        view = ActivityListaFilmesBinding.inflate(layoutInflater)
        setContentView(view.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (intent.hasExtra("userId")) {
            // Recupera o ID do Intent
            userId = intent.getIntExtra("userId", -1)
        }

        db = ListMovieOpenHelper(this)
        FilmesListAdapter = FilmesListAdapter(db.getAllMoviesById(userId.toString()), this)

        view.filmes.layoutManager = LinearLayoutManager(this)
        view.filmes.adapter = FilmesListAdapter

        view.logo.setOnClickListener{
            voltaMenu()
        }
    }

    override fun onResume(){
        super.onResume()
    }

    private fun voltaMenu() {
        startActivity(Intent(this, MovieMenu::class.java).apply { putExtra("userId", userId.toString()) })
    }
}