package com.mati.tracker_presentation.Meal.addMealsScreen

import com.mati.tracker_presentation.search.TrackableFoodUiState

data class AddMealsState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val trackableFood: List<TrackableFoodUiState> = emptyList()
)