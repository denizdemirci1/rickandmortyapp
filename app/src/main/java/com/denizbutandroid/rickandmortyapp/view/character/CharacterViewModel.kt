package com.denizbutandroid.rickandmortyapp.view.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denizbutandroid.rickandmortyapp.domain.character.GetCharacterInfoUseCase
import com.denizbutandroid.rickandmortyapp.domain.models.Character
import com.denizbutandroid.rickandmortyapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterInfoUseCase: GetCharacterInfoUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(CharacterUiState())
    val uiState: StateFlow<CharacterUiState> = _uiState.asStateFlow()

    private val characterId: Int = checkNotNull(savedStateHandle["id"])

    init {
        getCharacter(characterId)
    }

    private fun getCharacter(id: Int) {
        viewModelScope.launch {
            getCharacterInfoUseCase.invoke(id).let {
                when (it) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = null,
                            character = it.data
                        )
                    }
                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = it.message,
                            character = null
                        )
                    }
                }
            }
        }
    }
}

data class CharacterUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val character: Character? = null
)
