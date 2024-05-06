package com.example.mymovielist


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.mymovielist.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {

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
            val email = view.email.text.toString()
            val senha = view.senha.text.toString()

            if (db.verificarUsuario(email, senha)) {
                view.spinner.visibility = View.VISIBLE

                Handler(Looper.getMainLooper()).postDelayed({
                    view.spinner.visibility = View.INVISIBLE
                }, 2000)
                telaMovieMenu()
                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Credenciais inválidas!", Toast.LENGTH_SHORT).show()
            }
        }
        view.cadastrar.setOnClickListener {
            telaCadastro()
        }
    }

    private fun telaMovieMenu() {
        startActivity(Intent(this, MovieMenu::class.java).apply { putExtra("userId", db.getUserIdByName(view.email.text.toString())) })
        Toast.makeText(this, db.getUserIdByName(view.email.text.toString()).toString(), Toast.LENGTH_SHORT).show()
    }
    private fun telaCadastro() {
        startActivity(Intent(this, FormCadastro::class.java))
    }
}