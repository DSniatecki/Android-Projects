package com.example.android.supermovies.screens.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.supermovies.Actor
import com.example.android.supermovies.R
import kotlinx.android.synthetic.main.actor_recycle_element.view.*

class ActorsAdapter(private val actors: List<Actor>) :
        RecyclerView.Adapter<ActorsAdapter.ActorViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.actor_recycle_element, parent, false)
        )
    }

    override fun getItemCount() = actors.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actors[position]
        holder.view.actor_image.setImageResource(actor.image.resourceId)
        holder.view.actor_name.text = actor.name
    }


    class ActorViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}