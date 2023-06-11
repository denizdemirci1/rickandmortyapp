package com.denizbutandroid.rickandmortyapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.denizbutandroid.rickandmortyapp.view.character.CharacterScreen
import com.denizbutandroid.rickandmortyapp.view.characters.CharactersScreen
import com.denizbutandroid.rickandmortyapp.view.ui.theme.RickandmortyappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickandmortyappTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "characters"
                ) {
                    composable(
                        route = "characters"
                    ) {
                        CharactersScreen(
                            onNavigate = { id -> navController.navigate("character/$id") }
                        )
                    }

                    composable(
                        route = "character/{id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.IntType
                                defaultValue = 0
                            }
                        )
                    ) {
                        CharacterScreen()
                    }
                }
            }
        }
    }
}
