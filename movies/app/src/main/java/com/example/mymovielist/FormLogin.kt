package com.example.mymovielist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mymovielist.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding
    private lateinit var usuarioOpenHelper: UsuarioOpenHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        usuarioOpenHelper = UsuarioOpenHelper(this)
        inserirUsuarioExemplo()

        binding.button.setOnClickListener {
            val email = binding.email.text.toString()
            val senha = binding.senha.text.toString()

            // Verifica se o email e a senha estão corretos
            if (verificarCredenciais(email, senha)) {
                // Credenciais corretas, faça algo, como navegar para outra tela
                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
            } else {
                // Credenciais incorretas, exiba uma mensagem de erro
                Toast.makeText(this, "Credenciais inválidas!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inserirUsuarioExemplo() {
        val usuario = User("Exemplo", "exemplo@example.com", "senha123", 30)

        // Insira o usuário no banco de dados usando a função insertUser da classe UsuarioOpenHelper
        usuarioOpenHelper.insertUser(usuario)
    }
    private fun verificarCredenciais(email: String, password: String): Boolean {
        // Consulta o banco de dados para verificar se o usuário com o email e senha fornecidos existe
        return usuarioOpenHelper.verificarUsuario(email, password)
    }

    //private void fun
}