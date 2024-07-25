package com.mati.onboarding_presentation.personalInformation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mati.HealthyEating.R
import com.mati.core.domain.model.ActivityLevel
import com.mati.core.domain.model.Gender
import com.mati.core.domain.prefernces.Preferences
import com.mati.core.util.UiEvent
import com.mati.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    var age by mutableStateOf("20")
        private set

    fun onAgeEnter(age: String) {
        if (age.length <= 3) {
            this.age = age
        }
    }

    var height by mutableStateOf("180")
        private set

    fun onHeightEnter(height: String) {
        if (height.length <= 3) {
            this.height = height
        }
    }

    var weight by mutableStateOf("80.0")
        private set

    fun onWeightEnter(weight: String) {
        if (weight.length <= 5) {
            this.weight = weight
        }
    }

    var selectedGender by mutableStateOf<Gender>(Gender.Male)
        private set

    fun onGenderClick(gender: Gender) {
        selectedGender = gender
    }

    var selectedActivityLevel by mutableStateOf<ActivityLevel>(
        ActivityLevel.Medium
    )
        private set

    fun onActivityLevelSelect(activityLevel: ActivityLevel) {
        selectedActivityLevel = activityLevel
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onNextClick() {

        viewModelScope.launch {

            val ageNumber = age.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(R.string.error_age_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveAge(ageNumber)
            val heightNumber = height.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(R.string.error_height_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveHeight(heightNumber)

            val weightNumber = weight.toFloatOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(R.string.error_weight_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveWeight(weightNumber)

            preferences.saveGender(selectedGender)
            preferences.saveActivityLevel(selectedActivityLevel)
            _uiEvent.send(UiEvent.Success)
        }
    }

}