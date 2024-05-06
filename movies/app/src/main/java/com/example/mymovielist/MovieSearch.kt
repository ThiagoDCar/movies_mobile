package com.example.mymovielist

import android.app.appsearch.SearchResult
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mymovielist.Filme
import com.example.mymovielist.ResultadoBusca
import com.example.mymovielist.databinding.ActivityMovieSearchBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response



class MovieSearch : AppCompatActivity() {

    private lateinit var view: ActivityMovieSearchBinding
    private lateinit var db: ListMovieOpenHelper
    private val apiKey = "b2ca12b72a2d39fd17fa0c3fcfc37ae7"
    private lateinit var filme: Filme
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityMovieSearchBinding.inflate(layoutInflater)
        setContentView(view.root)
        db = ListMovieOpenHelper(this)

        if (intent.hasExtra("userId")) {
            // Recupera o ID do Intent
            userId = intent.getIntExtra("userId", -1)
        }

        view.button.setOnClickListener {
            val pesquisa = view.pesquisar.text.toString()

            // Verifica se o campo de pesquisa não está vazio
            if (pesquisa.isNotEmpty()) {
                // Inicia a corrotina para buscar o filme
                lifecycleScope.launch {
                    try {
                        val filmin = buscarFilme(pesquisa)
                        if(filmin != null){
                            filme = filmin
                        }
                        if (filme != null) {
                            // Exibe os detalhes do filme na tela
                            view.response.text = "Nome do Filme: ${filme.title}\n\nSinopse: ${filme.overview}"
                            Log.e("backdrop_path", "${filme.backdrop_path}")
                            filme.backdrop_path?.let { path ->
                                // Carrega a imagem usando Picasso
                                val pathImage = "https://image.tmdb.org/t/p/w500$path"

                                Picasso.get().load(pathImage).into(view.imageView)
                            }
                        } else {
                            view.response.text = "Filme não encontrado."
                        }
                    } catch (e: Exception) {
                        // Em caso de erro na requisição
                        Toast.makeText(this@MovieSearch, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Por favor, insira um termo de pesquisa.", Toast.LENGTH_SHORT).show()
            }
        }

        view.addList.setOnClickListener{
            if(filme != null){
                db.insertMovie(userId.toString(), filme)
                Toast.makeText(this, "Filme adicionado à lista!", Toast.LENGTH_SHORT).show()
            }
            Toast.makeText(this, "Erro, tente novamente mais tarde!", Toast.LENGTH_SHORT).show()
        }

        view.logo.setOnClickListener{
            voltaMenu()
        }
    }

    private fun voltaMenu() {
        startActivity(Intent(this, MovieMenu::class.java).apply { putExtra("userId", userId.toString()) })
    }

    private suspend fun buscarFilme(pesquisa: String): Filme? {
        return withContext(Dispatchers.IO) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(TheMovieDbApi::class.java)
            val response: Response<ResultadoBusca> = service.buscarFilmes(pesquisa, apiKey)


            if (response.isSuccessful) {
                val resultado = response.body()
                resultado?.let {
                    if (it.results.isNotEmpty()) {
                        return@withContext it.results[1]
                    }
                }
            }
            return@withContext null
        }
    }
}
interface TheMovieDbApi {
    @GET("search/movie")
    suspend fun buscarFilmes(@Query("query") query: String, @Query("api_key") apiKey: String): Response<ResultadoBusca>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}