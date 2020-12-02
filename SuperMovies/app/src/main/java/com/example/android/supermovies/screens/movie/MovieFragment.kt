package com.example.android.supermovies.screens.movie

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.supermovies.JSON_SERIALIZER
import com.example.android.supermovies.ListMovie
import com.example.android.supermovies.Movie
import com.example.android.supermovies.R
import com.example.android.supermovies.databinding.MovieFragmentBinding
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.movie_fragment.view.*
import java.lang.reflect.Type


class MovieFragment : Fragment() {

    private lateinit var binding: MovieFragmentBinding
    private lateinit var scenesRecyclerView: RecyclerView
    private lateinit var actorsRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val movie = extractMovieFromArguments()
        binding = createAndConfigureBinding(inflater, container, movie)
        scenesRecyclerView = createSceneRecyclerView(movie)
        actorsRecyclerView = createActorsRecyclerView(movie)
        binding.selectedMovieLayout.selected_movie_return_button.setOnClickListener { switchScreenToNext() }
        return binding.root

    }

    private fun createSceneRecyclerView(movie: Movie): RecyclerView {
        return binding.movieScenesRecycleView.apply {
            layoutManager = LinearLayoutManager(binding.movieScenesRecycleView.context)
            adapter = ScenesAdapter(movie.sceneImages.toList())
        }
    }

    private fun createActorsRecyclerView(movie: Movie): RecyclerView {
        return binding.movieActorsRecycleView.apply {
            layoutManager = LinearLayoutManager(binding.movieActorsRecycleView.context)
            adapter = ActorsAdapter(movie.actors.toList())
        }
    }

    private fun createAndConfigureBinding(inflater: LayoutInflater, container: ViewGroup?, movie: Movie): MovieFragmentBinding {
        val newViewBinding: MovieFragmentBinding = DataBindingUtil
                .inflate(inflater, R.layout.movie_fragment, container, false)
        newViewBinding.movieImage.setImageResource(movie.image.resourceId)
        newViewBinding.movieTitle.text = movie.title
        newViewBinding.movieCategory.text = movie.category.name
        newViewBinding.movieCategory.setTextColor(Color.parseColor(movie.category.color))
        newViewBinding.movieDescription.text = movie.description
        return newViewBinding
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() = switchScreenToNext()
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun extractMovieFromArguments(): Movie {
        val data = arguments?.let { MovieFragmentArgs.fromBundle(it).listMovie }.orEmpty();
        val myType: Type = object : TypeToken<ListMovie>() {}.type
        val listMovie: ListMovie = JSON_SERIALIZER.fromJson(data, myType);
        return listMovie.movie
    }

    private fun switchScreenToNext() {
        val action = MovieFragmentDirections.listToMovieAction()
        NavHostFragment.findNavController(this).navigate(action)
    }

}