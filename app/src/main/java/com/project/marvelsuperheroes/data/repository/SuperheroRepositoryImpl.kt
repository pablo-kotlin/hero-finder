package com.project.marvelsuperheroes.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.marvelsuperheroes.data.Resource
import com.project.marvelsuperheroes.data.api.ApiService
import com.project.marvelsuperheroes.data.model.Superhero
import com.project.marvelsuperheroes.utils.PrivateConstants.PUBLIC_KEY
import com.project.marvelsuperheroes.utils.generateHash
import com.project.marvelsuperheroes.utils.toTimestampString
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperheroRepositoryImpl(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : SuperheroRepository {

    override fun getSuperheroes(superheroName: String): LiveData<Resource<List<Superhero>>> {
        val result = MutableLiveData<Resource<List<Superhero>>>()
        result.postValue(Resource.loading(null))

        CoroutineScope(dispatcher).launch {
            try {
                val ts = System.currentTimeMillis().toTimestampString()
                val hash = ts.generateHash()
                val response = apiService.getSuperheroes(
                    PUBLIC_KEY,
                    ts,
                    hash,
                    superheroName,
                    "name",
                    0,
                    100
                )
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.status == "Ok") {
                        val comics = apiResponse.data.results
                        result.postValue(Resource.success(comics))
                    } else {
                        Log.e("SuperheroRepository", "Error: ${response.errorBody()}")
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

    override fun getSuperheroById(id: Int): LiveData<Resource<Superhero>> {
        val result = MutableLiveData<Resource<Superhero>>()
        result.postValue(Resource.loading(null))

        CoroutineScope(dispatcher).launch {
            try {
                val ts = System.currentTimeMillis().toTimestampString()
                val hash = ts.generateHash()
                val response = apiService.getSuperheroById(id, PUBLIC_KEY, ts, hash)
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.status == "Ok") {
                        val superhero = apiResponse.data.results[0]
                        result.postValue(Resource.success(superhero))
                    } else {
                        Log.e("SuperheroRepository", "Error: ${response.errorBody()}")
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