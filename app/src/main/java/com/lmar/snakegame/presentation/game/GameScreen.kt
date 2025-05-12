package com.lmar.snakegame.presentation.game

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lmar.snakegame.R
import com.lmar.snakegame.domain.model.Direction
import com.lmar.snakegame.domain.model.LevelEnum
import com.lmar.snakegame.domain.model.Position
import com.lmar.snakegame.presentation.components.ControlPanel
import com.lmar.snakegame.presentation.components.CurvedSnakeSegment
import com.lmar.snakegame.presentation.components.FoodItem
import com.lmar.snakegame.presentation.components.ShadowText
import com.lmar.snakegame.presentation.components.VisualSegmentType
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
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
                ShadowText(
                    text = stringResource(R.string.app_name),
                    fontFamily = MaterialTheme.typography.displayLarge.fontFamily!!,
                    fontSize = 32.sp,
                    textColor = MaterialTheme.colorScheme.primary,
                    shadowColor = MaterialTheme.colorScheme.primary
                )

                BasicText(text = "Nivel: ${level.label} Puntaje: ${gameState.snake.body.size}")
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
                    gameState.snake.body.forEachIndexed { index, segment ->
                        val type = getSegmentType(index, gameState.snake.body)

                        CurvedSnakeSegment(
                            x = segment.x,
                            y = segment.y,
                            direction = gameState.snake.direction,
                            cellSize = cellSizePx,
                            type = type
                        )
                    }

                    // Dibuja la comida
                    FoodItem(
                        x = gameState.food.position.x,
                        y = gameState.food.position.y,
                        cellSize = cellSizePx
                    )
                }
            }

            if (!gameState.isGameOver) {
                //Controles
                ControlPanel(
                    onLeftClick = { viewModel.changeDirection(Direction.LEFT) },
                    onUpClick = { viewModel.changeDirection(Direction.UP) },
                    onDownClick = { viewModel.changeDirection(Direction.DOWN) },
                    onRightClick = { viewModel.changeDirection(Direction.RIGHT) }
                )
            }
        }

        if (gameState.isGameOver) {
            // ✅ Capa semitransparente cuando está en pausa
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                Text("Game Over", style = MaterialTheme.typography.headlineMedium, color = Color.White)
            }

            LaunchedEffect(Unit) {
                delay(2000L) // 2000 milisegundos = 2 segundos
                onGameOver()
            }
        }
    }
}

fun getSegmentType(index: Int, body: List<Position>): VisualSegmentType {
    return when (index) {
        0 -> VisualSegmentType.HEAD
        body.lastIndex -> VisualSegmentType.TAIL
        else -> {
            val prev = body[index - 1]
            val current = body[index]
            val next = body[index + 1]

            val isTurn = (prev.x != next.x && prev.y != next.y)
            if (isTurn) VisualSegmentType.TURN else VisualSegmentType.STRAIGHT
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GameScreenPreview() {
    GameScreen(level = LevelEnum.EASY) {}
}