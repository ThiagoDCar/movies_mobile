package com.example.mymovielist

import android.app.appsearch.SearchResult
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
    private val apiKey = "b2ca12b72a2d39fd17fa0c3fcfc37ae7" // Substitua por sua chave API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityMovieSearchBinding.inflate(layoutInflater)
        setContentView(view.root)

        view.button.setOnClickListener {
            val pesquisa = view.pesquisar.text.toString()

            // Verifica se o campo de pesquisa não está vazio
            if (pesquisa.isNotEmpty()) {
                // Inicia a corrotina para buscar o filme
                lifecycleScope.launch {
                    try {
                        val filme = buscarFilme(pesquisa)
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