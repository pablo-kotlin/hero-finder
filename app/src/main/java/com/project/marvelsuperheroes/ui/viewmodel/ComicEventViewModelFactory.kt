package com.project.marvelsuperheroes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.marvelsuperheroes.data.repository.ComicEventRepository

class ComicEventViewModelFactory(private val repository: ComicEventRepository) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ComicEventViewModel::class.java)) {
            return ComicEventViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}