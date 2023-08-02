package com.project.marvelsuperheroes.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.marvelsuperheroes.data.model.Superhero
import com.project.marvelsuperheroes.databinding.ItemSuperheroBinding

class SuperheroAdapter(
    private var superheroes: List<Superhero>,
    private val listener: OnSuperheroClickListener
) :
    RecyclerView.Adapter<SuperheroAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemSuperheroBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val superhero = superheroes[position]
        holder.binding.tvSuperheroName.text = superhero.name
        holder.binding.tvSuperheroDescription.text = superhero.description
        Glide.with(holder.itemView.context)
            .load(superhero.thumbnail.path + "." + superhero.thumbnail.extension)
            .into(holder.binding.imgSuperhero)

        holder.itemView.setOnClickListener {
            listener.onSuperheroClick(superhero.id)
        }
    }

    override fun getItemCount() = superheroes.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newSuperheroes: List<Superhero>) {
        this.superheroes = newSuperheroes
        notifyDataSetChanged()
    }
}

