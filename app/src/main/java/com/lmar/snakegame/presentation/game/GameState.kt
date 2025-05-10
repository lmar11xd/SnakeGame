package com.lmar.snakegame.presentation.game

import com.lmar.snakegame.domain.model.Direction
import com.lmar.snakegame.domain.model.Food
import com.lmar.snakegame.domain.model.Position
import com.lmar.snakegame.domain.model.Snake

data class GameState(
    val snake: Snake = Snake(body = listOf(Position(5, 5), Position(5, 6)), direction = Direction.UP),
    val food: Food = Food(Position(10, 10)),
    val isGameOver: Boolean = false
)