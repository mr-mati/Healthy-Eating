package com.mati.tracker_presentation.tracker_main

import com.mati.tracker_domain.model.TrackedFood


sealed class TrackerEvent {
    object OnNextDayClick : TrackerEvent()
    object OnPreviousDayClick : TrackerEvent()
    data class OnToggleMealClick(val meal: Meals) : TrackerEvent()
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood) : TrackerEvent()
}
