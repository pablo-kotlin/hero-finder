package com.project.marvelsuperheroes.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.marvelsuperheroes.data.model.ComicEvent
import com.project.marvelsuperheroes.databinding.ItemComicEventBinding

class ComicEventAdapter(
    private var comicEvents: List<ComicEvent>,
) : RecyclerView.Adapter<ComicEventAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemComicEventBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemComicEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comicEvent = comicEvents[position]
        holder.binding.tvTitle.text = comicEvent.title
        holder.binding.tvDescription.text = comicEvent.description
    }

    override fun getItemCount() = comicEvents.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newComicEvents: List<ComicEvent>) {
        this.comicEvents = newComicEvents
        notifyDataSetChanged()
    }
}