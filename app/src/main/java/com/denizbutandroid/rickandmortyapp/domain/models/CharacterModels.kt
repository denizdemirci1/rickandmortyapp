package com.denizbutandroid.rickandmortyapp.domain.models

data class Characters(
    val characters: List<Character>
)

data class Character(
    val id: Int,
    val name: String,
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val image: String = "",
    val created: String = ""
)
