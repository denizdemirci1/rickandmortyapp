package com.denizbutandroid.rickandmortyapp.data.remote

import com.denizbutandroid.rickandmortyapp.data.models.CharacterInfoDTO
import com.denizbutandroid.rickandmortyapp.data.models.CharactersDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {

    @GET("character")
    suspend fun getCharacters(): CharactersDTO

    @GET("character/{id}")
    suspend fun getCharacterInfo(
        @Path("id") id: Int
    ): CharacterInfoDTO
}
