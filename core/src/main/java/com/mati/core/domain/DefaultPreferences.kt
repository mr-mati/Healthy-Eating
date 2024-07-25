package com.mati.core.domain

import android.content.SharedPreferences
import com.mati.core.domain.model.ActivityLevel
import com.mati.core.domain.model.Gender
import com.mati.core.domain.model.GoalType
import com.mati.core.domain.model.UserInfo
import com.mati.core.domain.prefernces.Preferences

class DefaultPreferences(
    val preferences: SharedPreferences
) : Preferences {
    override fun saveGender(gender: Gender) {
        preferences.edit().putString(Preferences.KEY_GENDER, gender.name).apply()
    }

    override fun saveAge(age: Int) {
        preferences.edit().putInt(Preferences.KEY_AGE, age).apply()
    }

    override fun saveWeight(weight: Float) {
        preferences.edit().putFloat(Preferences.KEY_WEIGHT, weight).apply()
    }

    override fun saveHeight(height: Int) {
        preferences.edit().putInt(Preferences.KEY_HEIGHT, height).apply()
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        preferences.edit().putString(Preferences.KEY_ACTIVITY_LEVEL, level.name).apply()
    }

    override fun saveGoalType(type: GoalType) {
        preferences.edit().putString(Preferences.KEY_GOAL_TYPE, type.name).apply()
    }

    override fun saveCarbRatio(ratio: Float) {
        preferences.edit().putFloat(Preferences.KEY_CARB_RATIO, ratio).apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        preferences.edit().putFloat(Preferences.KEY_PROTEIN_RATIO, ratio).apply()
    }

    override fun saveFatRatio(ratio: Float) {
        preferences.edit().putFloat(Preferences.KEY_FAT_RATIO, ratio).apply()
    }

    override fun loadUserInfo(): UserInfo {
        val age = preferences.getInt(Preferences.KEY_AGE, -1)
        val height = preferences.getInt(Preferences.KEY_HEIGHT, -1)
        val weight = preferences.getFloat(Preferences.KEY_WEIGHT, -1f)
        val genderString = preferences.getString(Preferences.KEY_GENDER, null)
        val activityLevelString = preferences.getString(Preferences.KEY_ACTIVITY_LEVEL, null)
        val goalType = preferences.getString(Preferences.KEY_GOAL_TYPE, null)
        val carbRatio = preferences.getFloat(Preferences.KEY_CARB_RATIO, -1f)
        val proteinRatio = preferences.getFloat(Preferences.KEY_PROTEIN_RATIO, -1f)
        val fatRatio = preferences.getFloat(Preferences.KEY_FAT_RATIO, -1f)

        return UserInfo(
            gender = Gender.fromString(genderString ?: "male"),
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.fromString(activityLevelString ?: "medium"),
            goalType = GoalType.fromString(goalType ?: "keep_weight"),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio
        )
    }

}