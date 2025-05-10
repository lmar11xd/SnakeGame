package com.lmar.snakegame.presentation.highscores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmar.snakegame.data.local.repository.ScoreRepository
import com.lmar.snakegame.domain.entity.ScoreEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HighScoresViewModel @Inject constructor(
    private val repository: ScoreRepository
) : ViewModel() {
    private val _topScores = MutableStateFlow<List<ScoreEntity>>(emptyList())
    val topScores: StateFlow<List<ScoreEntity>> = _topScores

    init {
        viewModelScope.launch {
            _topScores.value = repository.getTopScores()
        }
    }
}