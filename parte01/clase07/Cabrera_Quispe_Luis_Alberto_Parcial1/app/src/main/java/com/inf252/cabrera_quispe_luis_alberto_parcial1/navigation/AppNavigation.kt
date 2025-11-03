package com.inf252.cabrera_quispe_luis_alberto_parcial1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.inf252.cabrera_quispe_luis_alberto_parcial1.screens.FirstScreen
import com.inf252.cabrera_quispe_luis_alberto_parcial1.screens.SecondScreen
import com.inf252.cabrera_quispe_luis_alberto_parcial1.screens.ThirdScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route){
        composable (AppScreens.FirstScreen.route){
            FirstScreen(navController)//asi se mueve de pantalla en pantalla
        }
        composable (
            route = AppScreens.SecondScreen.route + "/{text}",
            arguments = listOf(navArgument(name = "text"){
                type = NavType.StringType
            })){
            SecondScreen(navController, it.arguments?.getString("text"))//con estas lineas se pasan un texto de una pantalla a otra
        }
        composable (
            route = AppScreens.ThirdScreen.route + "/{text}",
            arguments = listOf(navArgument(name = "text"){
                type = NavType.StringType
            })){
            ThirdScreen(navController, it.arguments?.getString("text"))//con estas lineas se pasan un texto de una pantalla a otra
        }
    }
}