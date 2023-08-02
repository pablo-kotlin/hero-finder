package com.project.marvelsuperheroes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.marvelsuperheroes.data.repository.FirebaseRepository
import com.project.marvelsuperheroes.ui.viewmodel.LoginViewModel
import junit.framework.TestCase.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class LoginViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginViewModel
    private lateinit var repository: FirebaseRepository

    @Before
    fun setup() {
        repository = mock(FirebaseRepository::class.java)
        viewModel = LoginViewModel(repository)
    }

    @Test
    fun `isUserNameValid returns true when email is valid`() {
        val validEmail = "test@example.com"
        assertTrue(viewModel.isUserNameValid(validEmail))
    }

    @Test
    fun `isUserNameValid returns false when email is invalid`() {
        val invalidEmail = "test@example"
        assertFalse(viewModel.isUserNameValid(invalidEmail))
    }

    @Test
    fun `isPasswordValid returns true when password length is greater than 6`() {
        val validPassword = "password"
        assertTrue(viewModel.isPasswordValid(validPassword))
    }

    @Test
    fun `isPasswordValid returns false when password length is less than 6`() {
        val invalidPassword = "pass"
        assertFalse(viewModel.isPasswordValid(invalidPassword))
    }

    @Test
    fun `checkPasswordConfirmation returns true when passwords match`() {
        val password = "password"
        val confirmPassword = "password"
        assertTrue(viewModel.checkPasswordConfirmation(password, confirmPassword))
    }

    @Test
    fun `checkPasswordConfirmation returns false when passwords do not match`() {
        val password = "password"
        val confirmPassword = "passwrd"
        assertFalse(viewModel.checkPasswordConfirmation(password, confirmPassword))
    }
}