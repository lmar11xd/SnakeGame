package com.lmar.snakegame.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_table")
data class ScoreEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val levelName: String,
    val score: Int,
    val date: Long = System.currentTimeMillis()
)