package com.example.android.supermovies.screens.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.supermovies.Image
import com.example.android.supermovies.R
import kotlinx.android.synthetic.main.scene_recycle_element.view.*

class ScenesAdapter(private val sceneImages: List<Image>) :
        RecyclerView.Adapter<ScenesAdapter.SceneViewHolder>() {

    class SceneViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SceneViewHolder {
        return SceneViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.scene_recycle_element, parent, false))
    }

    override fun getItemCount() = sceneImages.size

    override fun onBindViewHolder(holder: SceneViewHolder, position: Int) {
        val image = sceneImages[position]
        holder.view.scene_image.setImageResource(image.resourceId)
    }

}