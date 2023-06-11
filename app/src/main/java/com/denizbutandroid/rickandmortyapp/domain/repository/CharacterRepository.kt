package com.denizbutandroid.rickandmortyapp.domain.repository

import com.denizbutandroid.rickandmortyapp.domain.models.Character
import com.denizbutandroid.rickandmortyapp.domain.models.Characters
import com.denizbutandroid.rickandmortyapp.domain.util.Resource

interface CharacterRepository {

    suspend fun getCharacters(): Resource<Characters>

    suspend fun getCharacterInfo(id:Int): Resource<Character>

}
