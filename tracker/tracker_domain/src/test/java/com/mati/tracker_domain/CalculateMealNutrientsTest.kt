package com.mati.tracker_domain

import com.google.common.truth.Truth.assertThat
import com.mati.core.domain.model.ActivityLevel
import com.mati.core.domain.model.Gender
import com.mati.core.domain.model.GoalType
import com.mati.core.domain.model.UserInfo
import com.mati.core.domain.prefernces.Preferences
import com.mati.tracker_domain.model.MealType
import com.mati.tracker_domain.model.TrackedFood
import com.mati.tracker_domain.use_case.CalculateMealNutrients
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class CalculateMealNutrientsTest {

    lateinit var calculateMealNutrients: CalculateMealNutrients

    @Before
    fun setUp() {

        val preferences = mockk<Preferences>(relaxed = true)
        every { preferences.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 20,
            weight = 80f,
            height = 180,
            activityLevel = ActivityLevel.High,
            goalType = GoalType.GainWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )
        calculateMealNutrients = CalculateMealNutrients(preferences)
    }


    @Test
    fun `Calories for breakfast properly calculated`() {


        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                imageUrl = null,
                mealType = MealType.fromString(
                    listOf("breakFast", "lunch", "dinner", "snack").random()
                ),
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(1000)
            )
        }
        val result = calculateMealNutrients(trackedFoods)

        val breakFastCalories = result.mealNutrients.values
            .filter { it.mealType is MealType.Breakfast }
            .sumOf { it.calories }

        val expectedCalories = trackedFoods
            .filter { it.mealType is MealType.Breakfast }
            .sumOf { it.calories }

        assertThat(breakFastCalories).isEqualTo(expectedCalories)

    }


    @Test
    fun `Carbs for dinner properly calculated`() {


        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                imageUrl = null,
                mealType = MealType.fromString(
                    listOf("breakFast", "lunch", "dinner", "snack").random()
                ),
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(1000)
            )
        }
        val result = calculateMealNutrients(trackedFoods)

        val dinnerCarbs = result.mealNutrients.values
            .filter { it.mealType is MealType.Dinner }
            .sumOf { it.carbs }

        val expectedCarbs = trackedFoods
            .filter { it.mealType is MealType.Dinner }
            .sumOf { it.carbs }

        assertThat(dinnerCarbs).isEqualTo(expectedCarbs)

    }

}