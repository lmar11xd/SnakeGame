package com.lmar.snakegame.domain.usecase

import com.lmar.snakegame.domain.model.Food
import com.lmar.snakegame.domain.model.Snake
import javax.inject.Inject

class EatFoodUseCase @Inject constructor() {
    operator fun invoke(snake: Snake, food: Food): Pair<Snake, Boolean> {
        val head = snake.body.first()

        return if (head == food.position) {
            // Comer: crecer sin eliminar la cola
            val newBody = listOf(head) + snake.body
            Pair(snake.copy(body = newBody), true)
        } else {
            // No come
            Pair(snake, false)
        }
    }
}