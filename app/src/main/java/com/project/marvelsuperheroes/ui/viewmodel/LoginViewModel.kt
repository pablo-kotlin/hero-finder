package com.project.marvelsuperheroes.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.project.marvelsuperheroes.data.repository.FirebaseRepository

class LoginViewModel(private val repository: FirebaseRepository) : ViewModel() {

    fun signIn(email: String, password: String): Task<AuthResult> {
        return repository.login(email, password)
    }

    fun createUser(email: String, password: String): Task<AuthResult> {
        return repository.createUser(email, password)
    }

    fun isUserNameValid(username: String): Boolean {
        return username.isNotEmpty()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    fun checkPasswordConfirmation(password: String, passwordConfirm: String): Boolean {
        return password == passwordConfirm
    }

}
