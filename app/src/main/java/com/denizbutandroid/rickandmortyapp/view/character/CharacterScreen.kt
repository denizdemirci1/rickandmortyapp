package com.denizbutandroid.rickandmortyapp.view.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.denizbutandroid.rickandmortyapp.domain.models.Character
import com.denizbutandroid.rickandmortyapp.view.characters.CharacterImage
import com.denizbutandroid.rickandmortyapp.view.characters.CharacterInfo
import com.denizbutandroid.rickandmortyapp.view.common.ErrorView
import com.denizbutandroid.rickandmortyapp.view.common.LoadingView
import com.denizbutandroid.rickandmortyapp.view.ui.theme.RickandmortyappTheme

@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel = hiltViewModel()
) {
    val state by characterViewModel.uiState.collectAsState()
    when {
        state.isLoading -> LoadingView()
        state.errorMessage != null -> ErrorView(state.errorMessage)
        state.character != null -> {
            state.character?.let { CharacterScreenContent(it) }
        }
    }
}

@Composable
fun CharacterScreenContent(
    character: Character
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CharacterImage(
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
                .clip(RoundedCornerShape(8.dp)),
            imageData = character.image
        )
        CharacterInfo(
            character.name,
            character.gender,
            character.species
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterScreenContentPreview() {
    RickandmortyappTheme {
        val character1 = Character(
            id = 1,
            name = "Rick",
            type = "Human",
            gender = "Male"
        )
        CharacterScreenContent(
            character = character1
        )
    }
}

@Preview(widthDp = 600, heightDp = 360, showBackground = true)
@Composable
fun CharacterScreenContentTabletPreview() {
    RickandmortyappTheme {
        val character1 = Character(
            id = 1,
            name = "Rick",
            type = "Human",
            gender = "Male"
        )
        CharacterScreenContent(
            character = character1
        )
    }
}
