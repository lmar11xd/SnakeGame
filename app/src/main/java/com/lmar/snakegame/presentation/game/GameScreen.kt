package com.lmar.snakegame.presentation.game

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lmar.snakegame.domain.model.Direction
import com.lmar.snakegame.domain.model.LevelEnum
import com.lmar.snakegame.presentation.components.ControlPanel
import kotlinx.coroutines.delay

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun GameScreen(level: LevelEnum, onGameOver: () -> Unit) {
    val viewModel: GameViewModel = hiltViewModel()
    val gameState = viewModel.gameState.collectAsState().value

    LaunchedEffect(level) {
        viewModel.startGame(level)
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Estadísticas
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Snake Game")
                Text("Nivel: ${level.label}")
                Text("Puntaje: ${gameState.snake.body.size}")
            }

            //Tablero
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .weight(1f) // ocupa el espacio vertical disponible
            ) {

                val cellSizePx = min(maxWidth / viewModel.boardWidth, maxHeight / viewModel.boardHeight)

                Box(
                    Modifier
                        .size(cellSizePx * viewModel.boardWidth, cellSizePx * viewModel.boardHeight)
                        .background(Color.Black)
                        .align(Alignment.Center)
                ) {
                    // Dibuja la serpiente
                    gameState.snake.body.forEach { segment ->
                        Box(Modifier
                            .offset(x = cellSizePx * segment.x, y = cellSizePx * segment.y)
                            .size(cellSizePx)
                            .background(Color.Green))
                    }

                    // Dibuja la comida
                    Box(Modifier
                        .offset(x = cellSizePx * gameState.food.position.x, y = cellSizePx * gameState.food.position.y)
                        .size(cellSizePx)
                        .background(Color.Red))
                }
            }

            if (gameState.isGameOver) {
                Box(
                    Modifier.fillMaxSize().background(Color(0xAA000000)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Game Over", fontSize = 32.sp, color = Color.White)
                }

                LaunchedEffect(Unit) {
                    delay(2000L) // 2000 milisegundos = 2 segundos
                    onGameOver()
                }
            } else {
                //Controles
                ControlPanel(
                    onLeftClick = { viewModel.changeDirection(Direction.LEFT) },
                    onUpClick = { viewModel.changeDirection(Direction.UP) },
                    onDownClick = { viewModel.changeDirection(Direction.DOWN) },
                    onRightClick = { viewModel.changeDirection(Direction.RIGHT) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GameScreenPreview() {
    GameScreen(level = LevelEnum.EASY) {}
}