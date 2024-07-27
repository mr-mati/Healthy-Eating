package com.mati.HealthyEating.navigation

sealed class NavigationItems(val route:String){
    object SplashScreen : NavigationItems("splashScreen")
    object Welcome : NavigationItems("welcome")
    object PersonalInformation : NavigationItems("personalInformation")
    object FitnessGoals : NavigationItems("fitnessGoals")
    object TrackerScreen : NavigationItems("trackerScreen")
}