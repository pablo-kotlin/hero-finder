package com.project.marvelsuperheroes.data.repository

import androidx.lifecycle.LiveData
import com.project.marvelsuperheroes.data.Resource
import com.project.marvelsuperheroes.data.model.ComicEvent

interface ComicEventRepository {
    fun getComicsByCharacterId(characterId: Int): LiveData<Resource<List<ComicEvent>>>
    fun getEventsByCharacterId(characterId: Int): LiveData<Resource<List<ComicEvent>>>
}