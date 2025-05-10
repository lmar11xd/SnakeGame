package com.lmar.snakegame.domain.usecase

import com.lmar.snakegame.domain.model.Direction
import com.lmar.snakegame.domain.model.Position
import com.lmar.snakegame.domain.model.Snake
import javax.inject.Inject

class MoveSnakeUseCase @Inject constructor() {
    operator fun invoke(snake: Snake): Snake {
        val newHead = when (snake.direction) {
            Direction.UP -> Position(snake.body.first().x, snake.body.first().y - 1)
            Direction.DOWN -> Position(snake.body.first().x, snake.body.first().y + 1)
            Direction.LEFT -> Position(snake.body.first().x - 1, snake.body.first().y)
            Direction.RIGHT -> Position(snake.body.first().x + 1, snake.body.first().y)
        }
        val newBody = listOf(newHead) + snake.body.dropLast(1)
        return snake.copy(body = newBody)
    }
}