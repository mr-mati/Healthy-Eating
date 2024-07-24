package com.mati.core.domain.model

import com.mati.core.domain.model.ActivityLevel
import com.mati.core.domain.model.Gender
import com.mati.core.domain.model.GoalType

data class UserInfo(
    val gender: Gender,
    val age: Int,
    val weight: Float,
    val height: Int,
    val activityLevel: ActivityLevel,
    val goalType: GoalType,
    val carbRatio: Float,
    val proteinRatio: Float,
    val fatRatio: Float
)
