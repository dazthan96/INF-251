package com.inf251.navegacion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.inf251.navegacion.screens.FirstScreen
import com.inf251.navegacion.screens.SecondScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route) {
        composable (AppScreens.FirstScreen.route){
            FirstScreen(navController)
        }
        composable(route = AppScreens.SecondScreen.route + "/{fruta}",
            arguments = listOf(navArgument(name="fruta"){type = NavType.StringType})) {
            SecondScreen(navController = navController, it.arguments?.getString("fruta"))
        }
    }
}