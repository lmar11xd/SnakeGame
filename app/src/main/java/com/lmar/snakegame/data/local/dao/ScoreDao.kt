package com.lmar.snakegame.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lmar.snakegame.domain.entity.ScoreEntity

@Dao
interface ScoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScore(score: ScoreEntity)

    @Query("SELECT * FROM score_table ORDER BY score DESC LIMIT 10")
    suspend fun getTopScores(): List<ScoreEntity>

    @Query("DELETE FROM score_table")
    suspend fun clearScores()
}