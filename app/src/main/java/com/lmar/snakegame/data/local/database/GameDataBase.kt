package com.lmar.snakegame.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lmar.snakegame.data.local.dao.ScoreDao
import com.lmar.snakegame.domain.entity.ScoreEntity

@Database(entities = [ScoreEntity::class], version = 1)
abstract class GameDatabase : RoomDatabase() {
    abstract fun scoreDao(): ScoreDao
}