package com.example.mymovielist

data class Filme(
    val title:String,
    val backdrop_path: String?, // Caminho da imagem do pôster
    val overview: String?,
    val nota: String?
)
