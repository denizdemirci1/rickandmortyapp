package com.denizbutandroid.rickandmortyapp.view.character

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.denizbutandroid.rickandmortyapp.domain.character.GetCharacterInfoUseCase
import com.denizbutandroid.rickandmortyapp.domain.models.Character
import com.denizbutandroid.rickandmortyapp.domain.util.Resource
import com.denizbutandroid.rickandmortyapp.utils.CoroutineDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class CharacterViewModelTest {

    private lateinit var characterViewModel: CharacterViewModel
    private val mockSavedStateHandle: SavedStateHandle = mockk()
    private val mockCharacterInfoUseCase: GetCharacterInfoUseCase = mockk()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = CoroutineDispatcherRule()

    @Before
    fun setUp() {
        every { mockSavedStateHandle.get<Int>(any()) } returns 0
    }

    @Test
    fun `GIVEN characterViewModel WHEN initialized THEN state is loading`() {
        characterViewModel = CharacterViewModel(
            mockSavedStateHandle,
            mockCharacterInfoUseCase
        )
        val actualState = characterViewModel.uiState.value
        val expectedState = CharacterUiState()
        assert(actualState == expectedState)
    }

    @Test
    fun `GIVEN characterViewModel WHEN data received THEN state is updated with data`() {
        coEvery { mockCharacterInfoUseCase.invoke(any()) } returns Resource.Success(mockCharacter)
        characterViewModel = CharacterViewModel(
            mockSavedStateHandle,
            mockCharacterInfoUseCase
        )

        val actualState = characterViewModel.uiState.value
        val expectedState = CharacterUiState(
            isLoading = false,
            character = mockCharacter
        )

        assert(actualState == expectedState)
    }

    @Test
    fun `GIVEN characterViewModel WHEN error received THEN state is updated with error`() {
        coEvery { mockCharacterInfoUseCase.invoke(any()) } returns Resource.Error(message = "error")
        characterViewModel = CharacterViewModel(
            mockSavedStateHandle,
            mockCharacterInfoUseCase
        )

        val actualState = characterViewModel.uiState.value
        val expectedState = CharacterUiState(
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
