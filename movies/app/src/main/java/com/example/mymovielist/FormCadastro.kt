package com.example.mymovielist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mymovielist.databinding.ActivityFormCadastroBinding

class FormCadastro : AppCompatActivity() {

    private lateinit var view: ActivityFormCadastroBinding
    private lateinit var db: UsuarioOpenHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        view = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(view.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = UsuarioOpenHelper(this)

        view.button.setOnClickListener {
            val nome = view.nome.text.toString()
            val email = view.email.text.toString()
            val senha = view.senha.text.toString()

            val user = User(nome,email,senha)

            db.insertUser(user)
            finish()
            Toast.makeText(this, "Cadrasto realizado com sucesso!", Toast.LENGTH_SHORT).show()
        }

        view.voltaLogin.setOnClickListener{
            telaLogin()
        }
    }
    private fun telaLogin() {
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
    }
}