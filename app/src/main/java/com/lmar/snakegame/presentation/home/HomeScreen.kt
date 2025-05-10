package com.lmar.snakegame.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lmar.snakegame.domain.model.LevelEnum

@Composable
fun HomeScreen(navController: NavController, onStartGame: (LevelEnum) -> Unit) {
    val levels = LevelEnum.entries.toTypedArray()
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Snake Game", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(24.dp))
        levels.forEach { level ->
            Button(onClick = { onStartGame(level) }) {
                Text("Nivel ${level.label}")
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = { navController.navigate("highscores") }
        ) {
            Text("Puntajes más altos")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(rememberNavController()) {}
}