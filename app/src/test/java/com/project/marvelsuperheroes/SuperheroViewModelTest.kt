package com.project.marvelsuperheroes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.project.marvelsuperheroes.data.Resource
import com.project.marvelsuperheroes.data.model.Image
import com.project.marvelsuperheroes.data.model.Superhero
import com.project.marvelsuperheroes.data.repository.SuperheroRepository
import com.project.marvelsuperheroes.ui.viewmodel.SuperheroViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.util.Date

class SuperheroViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: SuperheroViewModel
    private lateinit var repository: SuperheroRepository

    @Before
    fun setup() {
        repository = mock(SuperheroRepository::class.java)
        viewModel = SuperheroViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getSuperheroes returns superheroes`() = runTest {
        // Arrange
        val date = Date()
        val image = Image("path", "jpg")
        val superheroes = listOf(
            Superhero(1, "Avengers", "Description", date, image),
            Superhero(2, "Hulk", "Description", date, image)
        )
        `when`(repository.getSuperheroes(anyString())).thenReturn(MutableLiveData(Resource.success(superheroes)))

        // Act
        val liveData = viewModel.getSuperheroes("")

        // Assert
        liveData.observeForever { resource ->
            assertEquals(Resource.Status.SUCCESS, resource.status)
            assertEquals(superheroes, resource.data)
        }
    }
}