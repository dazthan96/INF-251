package com.inf251.tarea6.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.inf251.tarea6.screens.FirstScreen
import com.inf251.tarea6.screens.SecondScreen
import com.inf251.tarea6.screens.ThirdScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route){
        composable (AppScreens.FirstScreen.route){
            FirstScreen(navController)
        }
        composable(route = AppScreens.SecondScreen.route+"/{textToQr}",
            arguments = listOf(navArgument(name = "textToQr"){type= NavType.StringType})){
            SecondScreen(navController=navController, it.arguments?.getString("textToQr")!!)
        }
        composable(route = AppScreens.ThirdScreen.route+"/{lat}/{lon}",
            arguments = listOf(
                navArgument(name = "lat"){type= NavType.StringType},
                navArgument(name = "lon"){type= NavType.StringType}
            )){
            ThirdScreen(
                navController = navController,
                lat = it.arguments?.getString("lat")?.toDoubleOrNull()?:0.0,
                lon = it.arguments?.getString("lon")?.toDoubleOrNull()?:0.0,
            )
        }
    }
}