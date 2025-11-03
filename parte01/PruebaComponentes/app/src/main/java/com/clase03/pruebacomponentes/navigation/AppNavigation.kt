package com.clase03.pruebacomponentes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.clase03.pruebacomponentes.screens.FirstScreen
import com.clase03.pruebacomponentes.screens.SecondScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route){
        composable(AppScreens.FirstScreen.route){
            FirstScreen(navController)
        }
        composable(AppScreens.SecondScreen.route + "/{text}",
            arguments = listOf(navArgument(name="text"){
                type= NavType.StringType
            })){
            SecondScreen(navController,it.arguments?.getString("text"))
        }
    }
}