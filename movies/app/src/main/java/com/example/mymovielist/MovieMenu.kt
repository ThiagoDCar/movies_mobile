package com.example.mymovielist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mymovielist.databinding.ActivityFormLoginBinding
import com.example.mymovielist.databinding.ActivityMovieMenuBinding

class MovieMenu : AppCompatActivity() {

    private lateinit var view: ActivityMovieMenuBinding
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        view = ActivityMovieMenuBinding.inflate(layoutInflater)
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

        view.Pesq.setOnClickListener{
            telaMovieSearch()
        }

        view.ListaFilmes.setOnClickListener{
            telaListaFilmes()
        }

        view.Pesq.setOnClickListener{
            Sair()
        }
    }

    private fun telaMovieSearch() {
        startActivity(Intent(this, MovieMenu::class.java).apply { putExtra("userId", userId.toString()) })
    }

    private fun telaListaFilmes() {
        //startActivity(Intent(this, ListaFilmes::class.java).apply { putExtra("userId", userId.toString() })
    }

    private fun Sair() {
        startActivity(Intent(this, FormLogin::class.java))
        Toast.makeText(this, "Até mais!", Toast.LENGTH_SHORT).show()
    }
}