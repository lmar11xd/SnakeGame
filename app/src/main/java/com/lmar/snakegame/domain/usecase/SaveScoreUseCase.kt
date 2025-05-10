package com.lmar.snakegame.domain.usecase

import com.lmar.snakegame.data.local.repository.ScoreRepository
import com.lmar.snakegame.domain.entity.ScoreEntity
import javax.inject.Inject

class SaveScoreUseCase @Inject constructor(private val repository: ScoreRepository) {
    suspend operator fun invoke(score: ScoreEntity) {
        repository.insertScore(score)
    }
}