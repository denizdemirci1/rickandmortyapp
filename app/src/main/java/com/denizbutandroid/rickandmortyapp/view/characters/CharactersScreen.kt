package com.denizbutandroid.rickandmortyapp.view.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.denizbutandroid.rickandmortyapp.R
import com.denizbutandroid.rickandmortyapp.domain.models.Character
import com.denizbutandroid.rickandmortyapp.view.common.ErrorView
import com.denizbutandroid.rickandmortyapp.view.common.LoadingView
import com.denizbutandroid.rickandmortyapp.view.ui.theme.RickandmortyappTheme

@Composable
fun CharactersScreen(
    charactersViewModel: CharactersViewModel = hiltViewModel(),
    onNavigate: (Int) -> Unit
) {
    val state by charactersViewModel.uiState.collectAsState()
    when {
        state.isLoading -> LoadingView()
        state.errorMessage != null -> ErrorView(state.errorMessage)
        state.characters.isNotEmpty() -> {
            CharactersScreenContent(
                characters = state.characters,
                onNavigate = onNavigate
            )
        }
    }
}

@Composable
fun CharactersScreenContent(
    characters: List<Character>,
    onNavigate: (Int) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(characters) { character ->
                CharacterView(
                    character = character,
                    modifier = Modifier.clickable { onNavigate.invoke(character.id) }
                )
            }
        }
    }
}

@Composable
fun CharacterView(
    character: Character,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CharacterImage(
            modifier = modifier
                .width(100.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(8.dp)),
            imageData = character.image
        )
        Spacer(modifier = Modifier.height(8.dp))
        CharacterInfo(character.name)
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterImage(
    modifier: Modifier = Modifier,
    imageData: String
) {
    Box(modifier = Modifier) {
        Image(
            painter = rememberImagePainter(
                data = imageData,
                builder = { placeholder(R.drawable.placeholder_character) }
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .testTag(imageData)
        )
    }
}

@Composable
fun CharacterInfo(
    vararg information: String
) {
    Column {
        information.forEach { info ->
            Text(text = info)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterScreenContentPreview() {
    RickandmortyappTheme {
        val character1 = Character(1, "Rick")
        val character2 = Character(2, "Morty")
        val character3 = Character(3, "Jerry")
        val character4 = Character(3, "Summer")
        val character5 = Character(3, "Beth")
        CharactersScreenContent(
            characters = listOf(
                character1,
                character2,
                character3,
                character4,
                character5
            ),
            onNavigate = {}
        )
    }
}

@Preview(widthDp = 600, heightDp = 300, showBackground = true)
@Composable
fun CharacterScreenContentTabletPreview() {
    RickandmortyappTheme {
        val character1 = Character(1, "Rick")
        val character2 = Character(2, "Morty")
        val character3 = Character(3, "Jerry")
        val character4 = Character(3, "Summer")
        val character5 = Character(3, "Beth")
        CharactersScreenContent(
            characters = listOf(
                character1,
                character2,
                character3,
                character4,
                character5
            ),
            onNavigate = {}
        )
    }
}

@Preview(widthDp = 100, heightDp = 100, showBackground = true)
@Composable
fun CharacterInfoPreview() {
    RickandmortyappTheme {
        CharacterInfo("Rick", "Human", "Male")
    }
}

@Preview(widthDp = 200, heightDp = 200, showBackground = true)
@Composable
fun CharacterImagePreview() {
    RickandmortyappTheme {
        CharacterImage(Modifier, "image.jpg")
    }
}

@Preview(widthDp = 200, heightDp = 200, showBackground = true)
@Composable
fun CharacterViewPreview() {
    RickandmortyappTheme {
        CharacterView(
            character = Character(1, "Rick"),
            modifier = Modifier
        )
    }
}
