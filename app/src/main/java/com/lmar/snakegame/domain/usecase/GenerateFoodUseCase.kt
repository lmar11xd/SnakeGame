package com.lmar.snakegame.domain.usecase

import com.lmar.snakegame.domain.model.Food
import com.lmar.snakegame.domain.model.Position
import com.lmar.snakegame.domain.model.Snake
import javax.inject.Inject

class GenerateFoodUseCase @Inject constructor() {
    operator fun invoke(snake: Snake, boardWidth: Int, boardHeight: Int): Food {
        val occupied = snake.body.toSet()
        val possiblePositions = mutableListOf<Position>()

        for (x in 0 until boardWidth) {
            for (y in 0 until boardHeight) {
                val pos = Position(x, y)
                if (pos !in occupied) {
                    possiblePositions.add(pos)
                }
            }
        }

        val randomPosition = possiblePositions.random()
        return Food(randomPosition)
    }
}