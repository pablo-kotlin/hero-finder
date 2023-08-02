package com.project.marvelsuperheroes.data.api

import com.project.marvelsuperheroes.data.model.ComicEvent
import com.project.marvelsuperheroes.data.model.Superhero
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v1/public/characters")
    suspend fun getSuperheroes(
        @Query("apikey") apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("nameStartsWith") nameStartsWith: String,
        @Query("orderBy") orderBy: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<ApiResponse<Superhero>>

    @GET("v1/public/characters/{id}")
    suspend fun getSuperheroById(
        @Path("id") id: Int,
        @Query("apikey") apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String
    ): Response<ApiResponse<Superhero>>

    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getComicsByCharacterId(
        @Path("characterId") characterId: Int,
        @Query("apikey") apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String
    ): Response<ApiResponse<ComicEvent>>

    @GET("v1/public/characters/{characterId}/events")
    suspend fun getEventsByCharacterId(
        @Path("characterId") characterId: Int,
        @Query("apikey") apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String
    ): Response<ApiResponse<ComicEvent>>
}
