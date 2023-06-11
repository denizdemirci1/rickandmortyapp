package com.denizbutandroid.rickandmortyapp.view.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.denizbutandroid.rickandmortyapp.view.ui.theme.RickandmortyappTheme

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

/**
 * Ideally, we can pass all the parameters to the ErrorView
 * such as color, fontSize, fontWeight etc
 * and make it more flexible and reusable. As we don't need
 * that much flexibility for this project, I am going to skip that.
 */
@Composable
fun ErrorView(
    message: String?
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red.copy(alpha = 0.5f))
    ) {
        Text(
            text = message ?: "Unknown error",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    RickandmortyappTheme {
        ErrorView("Could not fetch data")
    }
}

@Preview(widthDp = 300, heightDp = 300)
@Composable
fun LoadingPreview() {
    RickandmortyappTheme {
        LoadingView()
    }
}
