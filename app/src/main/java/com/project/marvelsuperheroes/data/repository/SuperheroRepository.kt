package com.project.marvelsuperheroes.data.repository

import androidx.lifecycle.LiveData
import com.project.marvelsuperheroes.data.Resource
import com.project.marvelsuperheroes.data.model.Superhero

interface SuperheroRepository {
    fun getSuperheroes(superheroName: String): LiveData<Resource<List<Superhero>>>
    fun getSuperheroById(id: Int): LiveData<Resource<Superhero>>
}