package com.denizbutandroid.rickandmortyapp.character

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class CharacterScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val characterScreenPom = CharacterScreenPageObjectModel(composeTestRule)

    @Test
    fun displaysCharacterContent() {
        with(characterScreenPom) {
            setContent()
            confirmImageDisplayed()
            confirmNameDisplayed()
            confirmGenderDisplayed()
            confirmTypeDisplayed()
        }
    }
}
