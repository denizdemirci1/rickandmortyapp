package com.denizbutandroid.rickandmortyapp.data.repositories

import com.denizbutandroid.rickandmortyapp.data.models.CharacterInfoDTO
import com.denizbutandroid.rickandmortyapp.data.models.CharactersDTO
import com.denizbutandroid.rickandmortyapp.data.remote.RickAndMortyService
import com.denizbutandroid.rickandmortyapp.di.IoDispatcher
import com.denizbutandroid.rickandmortyapp.domain.models.Character
import com.denizbutandroid.rickandmortyapp.domain.models.Characters
import com.denizbutandroid.rickandmortyapp.domain.repository.CharacterRepository
import com.denizbutandroid.rickandmortyapp.domain.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: RickAndMortyService,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher
): CharacterRepository {

    override suspend fun getCharacters(): Resource<Characters> {
        return withContext(defaultDispatcher) {
            try {
                Resource.Success(
                    data = service.getCharacters().toCharacters()
                )
            } catch(e: Exception) {
                Resource.Error(e.message ?: "An unknown error occurred.")
            }
        }
    }

    override suspend fun getCharacterInfo(id: Int): Resource<Character> {
        return withContext(defaultDispatcher) {
            try {
                Resource.Success(
                    data = service.getCharacterInfo(id).toCharacter()
                )
            } catch(e: Exception) {
                Resource.Error(e.message ?: "An unknown error occurred.")
            }
        }
    }
}

private fun CharactersDTO.toCharacters(): Characters {
    return Characters(
        characters = characters.map { it.toCharacter() }
    )
}

private fun CharacterInfoDTO.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image,
        created = created
    )
}
