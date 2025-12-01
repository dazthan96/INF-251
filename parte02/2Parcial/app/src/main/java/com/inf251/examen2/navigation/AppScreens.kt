package com.inf251.examen2.navigation

sealed class AppScreens (val route:String){
    object FirstScreen : AppScreens(route = "first_screen")
    object SecondScreen: AppScreens(route="second_screen")
    object ThirdScreen: AppScreens(route = "third_screen")
    object CameraScreen: AppScreens(route = "camera_screen")
}