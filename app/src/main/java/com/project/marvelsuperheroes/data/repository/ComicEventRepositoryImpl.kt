package com.project.marvelsuperheroes.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.marvelsuperheroes.data.Resource
import com.project.marvelsuperheroes.data.api.ApiService
import com.project.marvelsuperheroes.data.model.ComicEvent
import com.project.marvelsuperheroes.utils.PrivateConstants.PUBLIC_KEY
import com.project.marvelsuperheroes.utils.generateHash
import com.project.marvelsuperheroes.utils.toTimestampString
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ComicEventRepositoryImpl(
    private val apiServiceSuperhero: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ComicEventRepository {

    override fun getComicsByCharacterId(characterId: Int): LiveData<Resource<List<ComicEvent>>> {
        val result = MutableLiveData<Resource<List<ComicEvent>>>()
        result.postValue(Resource.loading(null))

        CoroutineScope(dispatcher).launch {
            try {
                val ts = System.currentTimeMillis().toTimestampString()
                val hash = ts.generateHash()
                val response = apiServiceSuperhero.getComicsByCharacterId(characterId, PUBLIC_KEY, ts, hash)

                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.status == "Ok") {
                        val comics = apiResponse.data.results
                        result.postValue(Resource.success(comics))
                    } else {
                        Log.e("ComicEventRepository", "Error: ${response.errorBody()}")
                    }
                } else {
                    result.postValue(Resource.error("Error: ${response.message()}", null))
                }
            } catch (e: Exception) {
                result.postValue(Resource.error("Exception: ${e.message}", null))
            }
        }
        return result
    }

    override fun getEventsByCharacterId(characterId: Int): LiveData<Resource<List<ComicEvent>>> {
        val result = MutableLiveData<Resource<List<ComicEvent>>>()
        result.postValue(Resource.loading(null))

        CoroutineScope(dispatcher).launch {
            try {
                val ts = System.currentTimeMillis().toTimestampString()
                val hash = ts.generateHash()
                val response = apiServiceSuperhero.getEventsByCharacterId(characterId, PUBLIC_KEY, ts, hash)

                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.status == "Ok") {
                        val events = apiResponse.data.results
                        result.postValue(Resource.success(events))
                    } else {
                        Log.e("ComicEventRepository", "Error: ${response.errorBody()}")
                    }
                } else {
                    result.postValue(Resource.error("Error: ${response.message()}", null))
                }
            } catch (e: Exception) {
                result.postValue(Resource.error("Exception: ${e.message}", null))
            }
        }
        return result
    }
}