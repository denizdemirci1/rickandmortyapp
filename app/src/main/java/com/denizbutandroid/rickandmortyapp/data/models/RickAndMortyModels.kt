package com.denizbutandroid.rickandmortyapp.data.models

import com.google.gson.annotations.SerializedName

data class CharactersDTO(
    @SerializedName("results")
    val characters: List<CharacterInfoDTO>
)

data class CharacterInfoDTO(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val created: String
)
