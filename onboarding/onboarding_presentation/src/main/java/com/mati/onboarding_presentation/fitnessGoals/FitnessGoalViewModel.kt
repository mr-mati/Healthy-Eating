package com.mati.onboarding_presentation.fitnessGoals

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mati.core.domain.model.GoalType
import com.mati.core.domain.prefernces.Preferences
import com.mati.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FitnessGoalViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    var selectedGoal by mutableStateOf<GoalType>(
        GoalType.KeepWeight
    )
        private set

    var carbsRatio by mutableStateOf(50f)
        private set

    fun onCarbsRatio(ratio: Float) {
        this.carbsRatio = ratio
    }

    var proteinRatio by mutableStateOf(50f)
        private set

    fun onProteinRatio(ratio: Float) {
        this.proteinRatio = ratio
    }

    var fatRatio by mutableStateOf(50f)
        private set

    fun onFatRatio(ratio: Float) {
        this.fatRatio = ratio
    }


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGoalTypeSelect(goalType: GoalType) {
        selectedGoal = goalType
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveGoalType(selectedGoal)

            preferences.saveCarbRatio(carbsRatio)
            preferences.saveProteinRatio(proteinRatio)
            preferences.saveFatRatio(fatRatio)

            _uiEvent.send(UiEvent.Success)
        }
    }
}