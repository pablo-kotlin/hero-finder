package com.project.marvelsuperheroes.data.api

data class ApiResponse<T>(
    val code: Int,
    val status: String,
    val data: DataContainer<T>
)