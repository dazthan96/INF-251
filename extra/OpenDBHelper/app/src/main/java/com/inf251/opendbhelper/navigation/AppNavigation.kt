package com.inf251.opendbhelper.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.inf251.opendbhelper.repository.PersonaRepository
import com.inf251.opendbhelper.screens.FirstScreen
import com.inf251.opendbhelper.screens.SecondScreen

@Composable
fun AppNavigation(repository: PersonaRepository){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route){
        composable (AppScreens.FirstScreen.route ){
            FirstScreen(navController, repository)
        }
        composable (AppScreens.SecondSreen.route+"/{ci}",
            arguments = listOf(navArgument(name="ci"){type= NavType.StringType;nullable=true})
        ){ SecondScreen(navController,it.arguments?.getString("ci")!!, repository) }
    }
}