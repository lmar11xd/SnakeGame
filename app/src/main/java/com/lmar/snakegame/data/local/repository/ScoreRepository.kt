package com.lmar.snakegame.data.local.repository

import com.lmar.snakegame.data.local.dao.ScoreDao
import com.lmar.snakegame.domain.entity.ScoreEntity
import javax.inject.Inject

class ScoreRepository @Inject constructor(private val dao: ScoreDao) {
    suspend fun insertScore(score: ScoreEntity) = dao.insertScore(score)
    suspend fun getTopScores() = dao.getTopScores()
}