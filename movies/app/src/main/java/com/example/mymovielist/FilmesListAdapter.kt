package com.example.mymovielist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FilmesListAdapter(private var filmes: List<Filme>, context: Context) :
    RecyclerView.Adapter<FilmesListAdapter.FilmesListHolder>() {

    class FilmesListHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val imagePathImageView: ImageView = itemView.findViewById(R.id.imageView)
        val overViewTextView: TextView = itemView.findViewById(R.id.overview)
        val rateTextView: TextView = itemView.findViewById(R.id.rate)
        val releaseTextView: TextView = itemView.findViewById(R.id.release_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.filme_item_list, parent, false)
        return FilmesListHolder(view)
    }

    override fun getItemCount(): Int = filmes.count()

    override fun onBindViewHolder(holder: FilmesListHolder, position: Int) {
        val filme = filmes[position]
        holder.titleTextView.text = "Título: " + filme.title
        holder.overViewTextView.text = "Sobre: " + filme.overview
        holder.rateTextView.text = "Nota: " + filme.vote_average
        holder.releaseTextView.text = "Lançamento: " + filme.release_date

        val pathImage = "https://image.tmdb.org/t/p/w500${filme.backdrop_path}"
        Picasso.get().load(pathImage).into(holder.imagePathImageView)
    }

    fun refreshData(newFilmes: List<Filme>){
        filmes = newFilmes
        notifyDataSetChanged()
    }
}