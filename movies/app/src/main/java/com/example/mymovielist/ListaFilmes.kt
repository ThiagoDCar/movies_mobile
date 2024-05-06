package com.example.mymovielist


import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mymovielist.databinding.ActivityFormLoginBinding
import com.example.mymovielist.databinding.ActivityListaFilmesBinding

class ListaFilmes : AppCompatActivity() {

    private lateinit var view: ActivityListaFilmesBinding
    private lateinit var db: UsuarioOpenHelper
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

        db = UsuarioOpenHelper(this)

        view.logo.setOnClickListener{
            voltaMenu()
        }
    }

    private fun voltaMenu() {
        startActivity(Intent(this, MovieMenu::class.java).apply { putExtra("userId", userId.toString() })
    }
}