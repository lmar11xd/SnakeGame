package com.lmar.snakegame.domain.model

enum class LevelEnum(val label: String, val speed: Long) {
    EASY("Fácil", 300L),
    MEDIUM("Medio", 150L),
    HARD("Difícil", 100L)
}