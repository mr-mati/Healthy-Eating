package com.mati.HealthyEating.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mati.onboarding_presentation.welcome.WelcomeScreen

@Composable
fun Navigation() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = NavigationItems.Welcome.route
    ) {
        composable(
            NavigationItems.Welcome.route,
        ) {
            WelcomeScreen()
        }
    }

}