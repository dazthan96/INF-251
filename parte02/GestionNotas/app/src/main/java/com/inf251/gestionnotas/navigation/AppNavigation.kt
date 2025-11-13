package com.inf251.gestionnotas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.inf251.gestionnotas.screens.DocenteContent
import com.inf251.gestionnotas.screens.DocenteScreen
import com.inf251.gestionnotas.screens.EstudianteContent
import com.inf251.gestionnotas.screens.EstudianteScreen
import com.inf251.gestionnotas.screens.MateriaContent
import com.inf251.gestionnotas.screens.MateriaScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.DocenteScreen.route ){
        composable(AppScreens.DocenteScreen.route){
            DocenteScreen(navController)
        }
        composable(AppScreens.EstudianteScreen.route){
            EstudianteScreen(navController)
        }
        composable (AppScreens.MateriaScreen.route){
            MateriaScreen(navController)
        }
    }
}