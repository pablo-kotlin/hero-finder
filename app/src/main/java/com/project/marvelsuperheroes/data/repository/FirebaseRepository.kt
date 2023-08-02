package com.project.marvelsuperheroes.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface FirebaseRepository {
    fun login(email: String, password: String): Task<AuthResult>
    fun createUser(email: String, password: String): Task<AuthResult>
}