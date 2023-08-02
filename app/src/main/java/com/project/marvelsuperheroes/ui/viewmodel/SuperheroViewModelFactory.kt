package com.project.marvelsuperheroes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.marvelsuperheroes.data.repository.SuperheroRepository

class SuperheroViewModelFactory(private val repository: SuperheroRepository) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuperheroViewModel::class.java)) {
            return SuperheroViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}