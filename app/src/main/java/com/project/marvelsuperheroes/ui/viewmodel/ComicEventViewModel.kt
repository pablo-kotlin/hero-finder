package com.project.marvelsuperheroes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.marvelsuperheroes.data.Resource
import com.project.marvelsuperheroes.data.model.ComicEvent
import com.project.marvelsuperheroes.data.repository.ComicEventRepository

class ComicEventViewModel(private val repository: ComicEventRepository): ViewModel() {

    fun getComicsByCharacterId(characterId: Int): LiveData<Resource<List<ComicEvent>>> {
        return repository.getComicsByCharacterId(characterId)
    }

    fun getEventsByCharacterId(characterId: Int): LiveData<Resource<List<ComicEvent>>> {
        return repository.getEventsByCharacterId(characterId)
    }
}
