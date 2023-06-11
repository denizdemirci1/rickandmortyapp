package com.denizbutandroid.rickandmortyapp.view.characters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.denizbutandroid.rickandmortyapp.domain.character.GetCharactersUseCase
import com.denizbutandroid.rickandmortyapp.domain.models.Character
import com.denizbutandroid.rickandmortyapp.domain.models.Characters
import com.denizbutandroid.rickandmortyapp.domain.util.Resource
import com.denizbutandroid.rickandmortyapp.utils.CoroutineDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class CharactersViewModelTest {

    private lateinit var charactersViewModel: CharactersViewModel
    private val mockCharactersUseCase: GetCharactersUseCase = mockk()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = CoroutineDispatcherRule()

    @Before
    fun setUp() {

    }

    @Test
    fun `GIVEN characterViewModel WHEN initialized THEN state is loading`() {
        charactersViewModel = CharactersViewModel(mockCharactersUseCase)
        val actualState = charactersViewModel.uiState.value
        val expectedState = CharactersUiState()
        assert(actualState == expectedState)
    }

    @Test
    fun `GIVEN characterViewModel WHEN data received THEN state is updated with data`() {
        coEvery { mockCharactersUseCase.invoke() } returns Resource.Success(mockCharacters)
        charactersViewModel = CharactersViewModel(mockCharactersUseCase)

        val actualState = charactersViewModel.uiState.value
        val expectedState = CharactersUiState(
            isLoading = false,
            characters = listOf(mockCharacter)
        )

        assert(actualState == expectedState)
    }

    @Test
    fun `GIVEN characterViewModel WHEN error received THEN state is updated with error`() {
        coEvery { mockCharactersUseCase.invoke() } returns Resource.Error(message = "error")
        charactersViewModel = CharactersViewModel(mockCharactersUseCase)

        val actualState = charactersViewModel.uiState.value
        val expectedState = CharactersUiState(
            isLoading = false,
            errorMessage = "error"
        )

        assert(actualState == expectedState)
    }
}

private val mockCharacter = Character(
    id = 1,
    name = "",
    status = "",
    species = "",
    type = "",
    gender = "",
    image = "",
    created = "",
)

private val mockCharacters = Characters(
    listOf(mockCharacter)
)