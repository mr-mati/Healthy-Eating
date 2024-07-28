package com.mati.tracker_presentation.Meal.addMealsScreen

import com.mati.tracker_domain.model.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)
