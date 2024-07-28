package com.mati.HealthyEating.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mati.HealthyEating.ui.SplashScreen.SplashScreen
import com.mati.core.domain.prefernces.Preferences
import com.mati.onboarding_presentation.fitnessGoals.FitnessGoals
import com.mati.onboarding_presentation.personalInformation.PersonalInformation
import com.mati.onboarding_presentation.welcome.WelcomeScreen
import com.mati.tracker_presentation.Meal.addMealsScreen.AddMealsScreen
import com.mati.tracker_presentation.tracker_main.TrackerScreen

@Composable
fun Navigation(
    scaffoldState: ScaffoldState,
    preferences: Preferences
) {
    val navHostController = rememberNavController()
    val shouldShowOnboarding = preferences.loadShouldShowOnboarding()

    NavHost(
        navController = navHostController,
        startDestination = NavigationItems.SplashScreen.route
    ) {

        composable(
            NavigationItems.SplashScreen.route,
        ) {
            SplashScreen() {
                if (shouldShowOnboarding) {
                    navHostController.navigate(NavigationItems.Welcome.route) {
                        popUpTo(NavigationItems.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                } else navHostController.navigate(NavigationItems.TrackerScreen.route) {
                    popUpTo(NavigationItems.SplashScreen.route) {
                        inclusive = true
                    }
                }
            }
        }

        composable(
            NavigationItems.Welcome.route,
        ) {
            WelcomeScreen() {
                navHostController.navigate(NavigationItems.PersonalInformation.route)
            }
        }

        composable(
            NavigationItems.PersonalInformation.route,
        ) {
            PersonalInformation(scaffoldState = scaffoldState, onNextClick = {
                navHostController.navigate(NavigationItems.FitnessGoals.route)
            })
        }

        composable(
            NavigationItems.FitnessGoals.route,
        ) {
            FitnessGoals() {
                navHostController.navigate(NavigationItems.TrackerScreen.route)
            }
        }

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