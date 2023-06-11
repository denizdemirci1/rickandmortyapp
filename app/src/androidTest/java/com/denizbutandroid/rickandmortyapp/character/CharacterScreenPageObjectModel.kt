package com.denizbutandroid.rickandmortyapp.character

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import com.denizbutandroid.rickandmortyapp.domain.models.Character
import com.denizbutandroid.rickandmortyapp.view.character.CharacterScreenContent

class CharacterScreenPageObjectModel(
    private val composeTestRule: ComposeContentTestRule
) {

    fun setContent() {
        composeTestRule.setContent {
            CharacterScreenContent(
                character = character
            )
        }
    }

    fun confirmImageDisplayed() = assertImageDisplayed(character.image)

    fun confirmNameDisplayed() = hasText(character.name)

    fun confirmGenderDisplayed() = hasText(character.gender)

    fun confirmTypeDisplayed() = hasText(character.type)

    private fun assertImageDisplayed(tag: String) {
        composeTestRule
            .onNodeWithTag(tag)
            .assertIsDisplayed()
    }

    private val character = Character(
        id = 1,
        name = "Rick",
        type = "Genius",
        gender = "Male",
        image = "Rick's image"
    )
}
