package com.denizbutandroid.rickandmortyapp.characters

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class CharactersScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val charactersScreenPom = CharactersScreenPageObjectModel(composeTestRule)

    @Test
    fun displaysCharactersContent() {
        with(charactersScreenPom) {
            setContent()
            confirmImagesDisplayed()
            confirmNamesDisplayed()
        }
    }
}
