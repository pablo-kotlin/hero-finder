package com.project.marvelsuperheroes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.marvelsuperheroes.data.Resource
import com.project.marvelsuperheroes.data.model.Superhero
import com.project.marvelsuperheroes.data.repository.SuperheroRepository

class SuperheroViewModel(private val repository: SuperheroRepository): ViewModel() {

    fun getSuperheroes(superheroName: String): LiveData<Resource<List<Superhero>>> {
        return repository.getSuperheroes(superheroName)
    }

    fun getSuperheroById(superheroId: Int): LiveData<Resource<Superhero>> {
        return repository.getSuperheroById(superheroId)
    }
}

