package com.inf251.opendbhelper.navigation

sealed class AppScreens(val route:String) {
    object FirstScreen: AppScreens(route = "first_screen")
    object SecondSreen: AppScreens(route = "second_screen")
}