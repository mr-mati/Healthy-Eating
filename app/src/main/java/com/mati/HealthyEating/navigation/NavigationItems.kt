package com.mati.HealthyEating.navigation

sealed class NavigationItems(val route:String){

    object Welcome : NavigationItems("welcome")
    object PersonalInformation : NavigationItems("personalInformation")
}