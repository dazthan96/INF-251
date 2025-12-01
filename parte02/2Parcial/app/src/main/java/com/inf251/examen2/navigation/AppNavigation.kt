package com.inf251.examen2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.inf251.examen2.screens.CameraScreen
import com.inf251.examen2.screens.FirstScreen
import com.inf251.examen2.screens.SecondScreen
import com.inf251.examen2.screens.ThirdScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route){
        composable (AppScreens.FirstScreen.route){
            FirstScreen(navController)
        }
        composable(AppScreens.SecondScreen.route){
            SecondScreen(navController)
        }
        composable(AppScreens.ThirdScreen.route){
            ThirdScreen(navController)
        }
        composable(AppScreens.CameraScreen.route) {
            CameraScreen(navController)
        }
    }
}