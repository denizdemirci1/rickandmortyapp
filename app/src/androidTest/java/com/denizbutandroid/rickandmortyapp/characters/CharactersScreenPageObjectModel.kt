package com.denizbutandroid.rickandmortyapp.characters

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import com.denizbutandroid.rickandmortyapp.domain.models.Character
import com.denizbutandroid.rickandmortyapp.view.characters.CharactersScreenContent

class CharactersScreenPageObjectModel(
    private val composeTestRule: ComposeContentTestRule
) {

    fun setContent() {
        composeTestRule.setContent {
            CharactersScreenContent(
                characters = characters,
                onNavigate = {}
            )
        }
    }

    fun confirmImagesDisplayed() = assertImageDisplayed(characters.map { it.image })

    fun confirmNamesDisplayed() = characters.forEach { character ->
        hasText(character.name)
    }

    private fun assertImageDisplayed(tags: List<String>) {
        tags.forEach { tag ->
            composeTestRule
                .onNodeWithTag(tag)
                .assertIsDisplayed()
        }
    }

    private val character1 = Character(
        id = 1,
        name = "Rick",
        type = "Genius",
        gender = "Male",
        image = "Rick's image"
    )

    private val character2 = Character(
        id = 2,
        name = "Morty",
        type = "Teenage",
        gender = "Male",
        image = "Morty's image"
    )

    private val characters = listOf(character1, character2)
}
