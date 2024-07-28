package com.mati.tracker_presentation.Meal.addMealsScreen

data class AddMealsState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val trackableFood: List<TrackableFoodUiState> = emptyList()
)