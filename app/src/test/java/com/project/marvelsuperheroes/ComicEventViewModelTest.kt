package com.project.marvelsuperheroes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.project.marvelsuperheroes.data.Resource
import com.project.marvelsuperheroes.data.model.ComicEvent
import com.project.marvelsuperheroes.data.repository.ComicEventRepository
import com.project.marvelsuperheroes.ui.viewmodel.ComicEventViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ComicEventViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: ComicEventViewModel
    private lateinit var repository: ComicEventRepository

    @Before
    fun setup() {
        repository = mock(ComicEventRepository::class.java)
        viewModel = ComicEventViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getComicsByCharacterId returns comics`() = runTest {
        // Arrange
        val comics = listOf(
            ComicEvent(1, "Comic 1", "Description 1"),
            ComicEvent(2, "Comic 2", "Description 2")
        )
        `when`(repository.getComicsByCharacterId(anyInt())).thenReturn(MutableLiveData(Resource.success(comics)))

        // Act
        val liveData = viewModel.getComicsByCharacterId(1)

        // Assert
        liveData.observeForever { resource ->
            assertEquals(Resource.Status.SUCCESS, resource.status)
            assertEquals(comics, resource.data)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getEventsByCharacterId returns events`() = runTest {
        // Arrange
        val events = listOf(
            ComicEvent(1, "Event 1", "Description 1"),
            ComicEvent(2, "Event 2", "Description 2")
        )
        `when`(repository.getEventsByCharacterId(anyInt())).thenReturn(MutableLiveData(Resource.success(events)))

        // Act
        val liveData = viewModel.getEventsByCharacterId(1)

        // Assert
        liveData.observeForever { resource ->
            assertEquals(Resource.Status.SUCCESS, resource.status)
            assertEquals(events, resource.data)
        }
    }
}
