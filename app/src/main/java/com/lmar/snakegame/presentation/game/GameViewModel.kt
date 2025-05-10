package com.lmar.snakegame.presentation.game

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmar.snakegame.domain.entity.ScoreEntity
import com.lmar.snakegame.domain.model.Direction
import com.lmar.snakegame.domain.model.LevelEnum
import com.lmar.snakegame.domain.usecase.CheckCollisionUseCase
import com.lmar.snakegame.domain.usecase.EatFoodUseCase
import com.lmar.snakegame.domain.usecase.GenerateFoodUseCase
import com.lmar.snakegame.domain.usecase.MoveSnakeUseCase
import com.lmar.snakegame.domain.usecase.SaveScoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val moveSnake: MoveSnakeUseCase,
    private val checkCollision: CheckCollisionUseCase,
    private val generateFood: GenerateFoodUseCase,
    private val eatFood: EatFoodUseCase,
    private val saveScore: SaveScoreUseCase
) : ViewModel() {
    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState

    private val _direction = MutableStateFlow(Direction.RIGHT)
    val direction: StateFlow<Direction> = _direction

    var boardWidth = 20
    var boardHeight = 30

    fun startGame(level: LevelEnum) {
        _gameState.value = GameState()
        viewModelScope.launch {
            while (!_gameState.value.isGameOver) {
                delay(level.speed)

                // Mover la serpiente
                val movedSnake = moveSnake(_gameState.value.snake.copy(direction = _direction.value))

                // Verificar colisión
                val collision = checkCollision(movedSnake, boardWidth, boardHeight)
                if (collision) {
                    _gameState.value = _gameState.value.copy(
                        isGameOver = true
                    )
                    endGame(level, _gameState.value.snake.body.size)
                    break
                }

                // Verificar si come
                val (newSnake, ate) = eatFood(movedSnake, _gameState.value.food)
                val newFood = if (ate) generateFood(newSnake, boardWidth, boardHeight)
                else _gameState.value.food

                // Actualizar estado
                _gameState.value = _gameState.value.copy(
                    snake = newSnake,
                    food = newFood
                )
            }
        }
    }

    fun changeDirection(newDirection: Direction) {
        val current = _direction.value
        // Evitar retroceso directo
        if ((current == Direction.UP && newDirection != Direction.DOWN) ||
            (current == Direction.DOWN && newDirection != Direction.UP) ||
            (current == Direction.LEFT && newDirection != Direction.RIGHT) ||
            (current == Direction.RIGHT && newDirection != Direction.LEFT)) {
            _direction.value = newDirection
        }
    }

    fun endGame(level: LevelEnum, score: Int) {
        Log.e("GameViewModel", "End game with score: $score")
        viewModelScope.launch {
            saveScore(ScoreEntity(levelName = level.name, score = score))
        }
    }
}