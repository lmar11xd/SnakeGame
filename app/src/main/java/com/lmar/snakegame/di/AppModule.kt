package com.lmar.snakegame.di

import android.content.Context
import androidx.room.Room
import com.lmar.snakegame.data.local.dao.ScoreDao
import com.lmar.snakegame.data.local.database.GameDatabase
import com.lmar.snakegame.data.local.repository.ScoreRepository
import com.lmar.snakegame.domain.usecase.SaveScoreUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GameDatabase =
        Room.databaseBuilder(context, GameDatabase::class.java, "snakegame_db").build()

    @Provides
    @Singleton
    fun provideScoreDao(db: GameDatabase): ScoreDao = db.scoreDao()


    @Provides
    @Singleton
    fun provideScoreRepository(scoreDao: ScoreDao): ScoreRepository {
        return ScoreRepository(scoreDao)
    }

    @Provides
    @Singleton
    fun provideSaveScoreUseCase(scoreRepository: ScoreRepository): SaveScoreUseCase {
        return SaveScoreUseCase(scoreRepository)
    }
/*
    @Provides
    @Singleton
    fun provideGetTopScoresUseCase(scoreRepository: ScoreRepository): GetTopScoresUseCase {
        return GetTopScoresUseCase(scoreRepository)
    }*/
}