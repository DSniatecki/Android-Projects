package com.example.android.supermovies.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.supermovies.Category
import com.example.android.supermovies.JSON_SERIALIZER
import com.example.android.supermovies.R
import com.example.android.supermovies.databinding.MainListFragmentBinding
import java.util.*


class ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: LinearLayoutManager
    private lateinit var viewModel: ListViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: MainListFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.main_list_fragment, container, false)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewManager = LinearLayoutManager(binding.movieListRecycleView.context)
        viewAdapter = ListMoviesAdapter(viewModel.listMovies, this::switchScreenToSelectedMovie)
        recyclerView = binding.movieListRecycleView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
        binding.movieCategorySpinner.onItemSelectedListener = createCategorySelectorListner()
        val itemTouchHelper = ItemTouchHelper(createSwipeCallback())
        itemTouchHelper.attachToRecyclerView(recyclerView)
        return binding.root
    }

    private fun createCategorySelectorListner(): OnItemSelectedListener {
        return object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                parent.setSelection(position)
                val rawCategory = resources.getStringArray(R.array.movie_category_array)[position]
                if (rawCategory.toLowerCase(Locale.ROOT) == "all") {
                    viewModel.loadAllMovies()
                } else {
                    val category = Category.valueOf(rawCategory.toUpperCase(Locale.ROOT))
                    viewModel.reloadAllMoviesFromCategory(category)
                }
                viewAdapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.loadAllMovies()
                viewAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun createSwipeCallback(): ItemTouchHelper.SimpleCallback {
        return object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position: Int = viewHolder.adapterPosition
                when (direction) {
                    ItemTouchHelper.LEFT, ItemTouchHelper.RIGHT -> {
                        viewModel.listMovies.removeAt(position)
                        viewAdapter.notifyItemRemoved(position)
                    }
                }
            }
        }
    }

    private fun switchScreenToSelectedMovie(position: Int) {
        val action = ListFragmentDirections.actionTitleToGame()
        action.listMovie = JSON_SERIALIZER.toJson(viewModel.listMovies[position])
        NavHostFragment.findNavController(this).navigate(action)
    }

}
