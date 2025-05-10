package com.lmar.snakegame.domain.usecase

import com.lmar.snakegame.domain.model.Snake
import javax.inject.Inject

class CheckCollisionUseCase @Inject constructor() {
    operator fun invoke(snake: Snake, boardWidth: Int, boardHeight: Int): Boolean {
        val head = snake.body.first()

        // Colisión con bordes
        if (head.x < 0 || head.y < 0 || head.x >= boardWidth || head.y >= boardHeight) {
            return true
        }

        // Colisión con su propio cuerpo (ignoramos la cabeza)
        if (snake.body.drop(1).contains(head)) {
            return true
        }

        return false
    }
}