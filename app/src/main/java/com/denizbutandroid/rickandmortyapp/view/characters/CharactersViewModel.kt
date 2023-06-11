package com.denizbutandroid.rickandmortyapp.view.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denizbutandroid.rickandmortyapp.domain.character.GetCharactersUseCase
import com.denizbutandroid.rickandmortyapp.domain.models.Character
import com.denizbutandroid.rickandmortyapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(CharactersUiState())
    val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            getCharactersUseCase.invoke().let {
                when (it) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = null,
                            characters = it.data?.characters ?: emptyList()
                        )
                    }
                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = it.message,
                            characters = emptyList()
                        )
                    }
                }
            }
        }
    }
}

data class CharactersUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val characters: List<Character> = emptyList()
)
