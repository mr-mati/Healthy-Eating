package com.mati.tracker_presentation.Meal.addMealsScreen

import com.mati.tracker_domain.model.MealType
import com.mati.tracker_domain.model.TrackableFood
import java.time.LocalDate

sealed class AddMealsEvent {
    data class OnQueryChange(val query: String) : AddMealsEvent()
    object OnSearch : AddMealsEvent()
    data class OnToggleTrackableFood(val food: TrackableFood) : AddMealsEvent()
    data class OnAmountForFoodChange(
        val food: TrackableFood,
        val amount: String
    ) : AddMealsEvent()
    data class OnTrackFoodClick(
        val food: TrackableFood,
        val mealType: MealType,
        val date: LocalDate
    ): AddMealsEvent()
    data class OnSearchFocusChange(val isFocused: Boolean): AddMealsEvent()
}
