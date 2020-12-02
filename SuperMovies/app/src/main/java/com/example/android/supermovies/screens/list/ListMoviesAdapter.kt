package com.example.android.supermovies.screens.list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.supermovies.R
import com.example.android.supermovies.ListMovie
import kotlinx.android.synthetic.main.list_movie_recycle_element.view.*

class ListMoviesAdapter(private val listMovies: MutableList<ListMovie>, private val onMovieClick: (Int) -> Unit) :
        RecyclerView.Adapter<ListMoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_movie_recycle_element, parent, false)
        )
    }

    override fun getItemCount() = listMovies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val listMovie = listMovies[position]
        val movie = listMovie.movie
        holder.view.movie_title.text = movie.title
        holder.view.movie_category.text = movie.category.name
        holder.view.movie_image.setImageResource(movie.image.resourceId)
        holder.view.movie_scenes_layout.setBackgroundColor(Color.parseColor(movie.category.color))
        holder.view.favourite_switch.isChecked = listMovie.isFavorite
        holder.view.setOnClickListener { onMovieClick(position) }
        holder.view.favourite_switch.setOnClickListener {
            onFavouriteClick(listMovie)
        }
    }

    private fun onFavouriteClick(selectedListMovie: ListMovie) {
        selectedListMovie.isFavorite = !selectedListMovie.isFavorite
        val newMovies = getSortedListMovies()
        listMovies.clear()
        listMovies.addAll(newMovies)
        this.notifyDataSetChanged()
    }

    private fun getSortedListMovies(): List<ListMovie> = listMovies
            .sortedBy { listMovie -> !listMovie.isFavorite }
            .sortedBy { listMovie -> listMovie.movie.category.order }

}