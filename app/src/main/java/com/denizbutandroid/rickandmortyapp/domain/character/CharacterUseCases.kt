package com.denizbutandroid.rickandmortyapp.domain.character

import com.denizbutandroid.rickandmortyapp.di.IoDispatcher
import com.denizbutandroid.rickandmortyapp.domain.models.Character
import com.denizbutandroid.rickandmortyapp.domain.models.Characters
import com.denizbutandroid.rickandmortyapp.domain.repository.CharacterRepository
import com.denizbutandroid.rickandmortyapp.domain.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetCharactersUseCase(
    private val characterRepository: CharacterRepository,
) {

    suspend operator fun invoke(): Resource<Characters> {
        return characterRepository.getCharacters()
    }
}

class GetCharacterInfoUseCase(
    private val characterRepository: CharacterRepository,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(id: Int): Resource<Character> {
        return withContext(defaultDispatcher) {
            characterRepository.getCharacterInfo(id)
        }
    }
}
