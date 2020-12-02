package com.example.android.supermovies.screens.list

import androidx.lifecycle.ViewModel
import com.example.android.supermovies.Category
import com.example.android.supermovies.ListMovie
import com.example.android.supermovies.MOVIES

class ListViewModel : ViewModel() {

    private val allListMovies = MOVIES.map { movie -> ListMovie(movie, false) }
    val listMovies: MutableList<ListMovie> = getAllMovies().toMutableList()


    fun reloadAllMoviesFromCategory(selectedCategory: Category) {
        listMovies.clear()
        listMovies.addAll(
                allListMovies
                        .filter { listMovie -> listMovie.movie.category == selectedCategory }
                        .sortedBy { listMovie -> !listMovie.isFavorite }
        )
    }

    fun loadAllMovies() {
        listMovies.clear()
        listMovies.addAll(getAllMovies())
    }

    private fun getAllMovies(): List<ListMovie> {
        return allListMovies
                .sortedBy { listMovie -> !listMovie.isFavorite }
                .sortedBy { listMovie -> listMovie.movie.category.order }
    }

}