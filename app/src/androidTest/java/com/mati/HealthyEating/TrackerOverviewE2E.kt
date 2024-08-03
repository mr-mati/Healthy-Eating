package com.mati.HealthyEating

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.common.truth.Truth.assertThat
import com.mati.HealthyEating.navigation.NavigationItems
import com.mati.HealthyEating.repository.TrackerRepositoryFake
import com.mati.HealthyEating.ui.theme.HealthyEatingTheme
import com.mati.core.domain.model.ActivityLevel
import com.mati.core.domain.model.Gender
import com.mati.core.domain.model.GoalType
import com.mati.core.domain.model.UserInfo
import com.mati.core.domain.prefernces.Preferences
import com.mati.core.use_case.FilterOutDigits
import com.mati.tracker_domain.model.TrackableFood
import com.mati.tracker_domain.use_case.CalculateMealNutrients
import com.mati.tracker_domain.use_case.DeleteTrackedFood
import com.mati.tracker_domain.use_case.GetFoodsForDate
import com.mati.tracker_domain.use_case.SearchFood
import com.mati.tracker_domain.use_case.TrackFood
import com.mati.tracker_domain.use_case.TrackerUseCases
import com.mati.tracker_presentation.Meal.addMealsScreen.AddMealsScreen
import com.mati.tracker_presentation.Meal.addMealsScreen.AddMealsViewModel
import com.mati.tracker_presentation.tracker_main.TrackerScreen
import com.mati.tracker_presentation.tracker_main.TrackerViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.math.roundToInt

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@HiltAndroidTest
class TrackerOverviewE2E {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var repositoryFake: TrackerRepositoryFake
    private lateinit var trackerUseCase: TrackerUseCases
    private lateinit var preferences: Preferences
    private lateinit var trackerViewModel: TrackerViewModel
    private lateinit var addMealsViewModel: AddMealsViewModel

    private lateinit var navController: NavHostController

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Before
    fun setUp() {

        preferences = mockk(relaxed = true)
        every { preferences.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 20,
            weight = 80f,
            height = 180,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )

        repositoryFake = TrackerRepositoryFake()

        trackerUseCase = TrackerUseCases(
            trackFood = TrackFood(repositoryFake),
            searchFood = SearchFood(repositoryFake),
            getFoodsForDate = GetFoodsForDate(repositoryFake),
            deleteTrackedFood = DeleteTrackedFood(repositoryFake),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )

        trackerViewModel = TrackerViewModel(
            preferences = preferences,
            trackerUseCases = trackerUseCase
        )

        addMealsViewModel = AddMealsViewModel(
            trackerUseCases = trackerUseCase,
            filterOutDigits = FilterOutDigits()
        )

        composeRule.setContent {
            HealthyEatingTheme {
                val scaffoldState = rememberScaffoldState()
                val navHostController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavigationItems.TrackerScreen.route
                    ) {

                        composable(
                            NavigationItems.TrackerScreen.route,
                        ) {
                            TrackerScreen(onNavigateToSearch = { day, month, year ->
                                navHostController.navigate(
                                    NavigationItems.SearchScreen.route +
                                            "/$day" +
                                            "/$month" +
                                            "/$year"
                                )
                            })
                        }

                        composable(
                            route = NavigationItems.SearchScreen.route + "/{dayOfMonth}/{month}/{year}",
                            arguments = listOf(
                                navArgument("dayOfMonth") {
                                    type = NavType.IntType
                                },
                                navArgument("month") {
                                    type = NavType.IntType
                                },
                                navArgument("year") {
                                    type = NavType.IntType
                                },
                            )
                        ) {
                            val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                            val month = it.arguments?.getInt("month")!!
                            val year = it.arguments?.getInt("year")!!
                            AddMealsScreen(
                                scaffoldState = scaffoldState,
                                dayOfMonth = dayOfMonth,
                                month = month,
                                year = year,
                                onNavigateUp = {
                                    navHostController.navigateUp()
                                }
                            )
                        }

                    }
                }
            }
        }
    }

    @SuppressLint("CheckResult")
    @Test
    fun addBreakfast_appearsUnderBreakfast_nutrientsProperlyCalculated() {
        repositoryFake.searchResults = listOf(
            TrackableFood(
                name = "banana",
                imageUrl = null,
                caloriesPer100g = 150,
                proteinPer100g = 5,
                carbsPer100g = 50,
                fatPer100g = 1
            )
        )

        val addedAmount = 150
        val expectedCalories = (1.5f * 150).roundToInt()
        val expectedCarbs = (1.5f * 50).roundToInt()
        val expectedProtein = (1.5f * 5).roundToInt()
        val expectedFat = (1.5f * 1).roundToInt()

        composeRule
            .onNodeWithText("Add Meals")
            .assertDoesNotExist()
        composeRule
            .onNodeWithContentDescription("Breakfast")
            .performClick()
        composeRule
            .onNodeWithText("Add Meals")
            .assertIsDisplayed()
        composeRule
            .onNodeWithText("Add Meals")
            .performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(NavigationItems.SearchScreen.route)
        ).isTrue()

        composeRule
            .onNodeWithTag("search_textfield")
            .performTextInput("banana")
        composeRule
            .onNodeWithContentDescription("Search...")
            .performClick()

        composeRule.onRoot().printToLog("COMPOSE TREE")

        composeRule
            .onNodeWithText("Carbs")
            .performClick()
        composeRule
            .onNodeWithContentDescription("Amount")
            .performTextInput(addedAmount.toStr())
        composeRule
            .onNodeWithContentDescription("Track")
            .performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(NavigationItems.TrackerScreen.route)
        )

        composeRule
            .onAllNodesWithText(expectedCarbs.toStr())
            .onFirst()
            .assertIsDisplayed()
        composeRule
            .onAllNodesWithText(expectedProtein.toStr())
            .onFirst()
            .assertIsDisplayed()
        composeRule
            .onAllNodesWithText(expectedFat.toStr())
            .onFirst()
            .assertIsDisplayed()
        composeRule
            .onAllNodesWithText(expectedCalories.toStr())
            .onFirst()
            .assertIsDisplayed()

    }


}
