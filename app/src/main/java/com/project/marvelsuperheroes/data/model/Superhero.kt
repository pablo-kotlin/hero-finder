package com.project.marvelsuperheroes.data.model

import java.util.Date

data class Superhero(
    val id: Int,
    val name: String,
    val description: String,
    val modified: Date,
    val thumbnail: Image
)