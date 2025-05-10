package com.lmar.snakegame.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lmar.snakegame.domain.model.LevelEnum
import com.lmar.snakegame.presentation.game.GameScreen
import com.lmar.snakegame.presentation.highscores.HighScoresScreen
import com.lmar.snakegame.presentation.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "menu") {
        composable("menu") {
            HomeScreen(navController) { level ->
                navController.navigate("game/${level.name}")
            }
        }
        composable(
            "game/{level}",
            arguments = listOf(
                navArgument("level") { type = NavType.StringType }
            )
        ) {
            val levelName = it.arguments?.getString("level") ?: LevelEnum.EASY.name
            GameScreen(level = LevelEnum.valueOf(levelName)) {
                navController.navigate("menu")
            }
        }
        composable("highscores") {
            HighScoresScreen()
        }
    }
}