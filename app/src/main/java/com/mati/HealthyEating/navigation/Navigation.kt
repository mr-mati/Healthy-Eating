package com.mati.HealthyEating.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mati.HealthyEating.ui.SplashScreen.SplashScreen
import com.mati.onboarding_presentation.fitnessGoals.FitnessGoals
import com.mati.onboarding_presentation.personalInformation.PersonalInformation
import com.mati.onboarding_presentation.welcome.WelcomeScreen
import com.mati.tracker_presentation.tracker_main.TrackerScreen

@Composable
fun Navigation(scaffoldState: ScaffoldState) {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = NavigationItems.SplashScreen.route
    ) {

        composable(
            NavigationItems.SplashScreen.route,
        ) {
            SplashScreen() {
                navHostController.navigate(NavigationItems.Welcome.route) {
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
            TrackerScreen()
        }

    }

}