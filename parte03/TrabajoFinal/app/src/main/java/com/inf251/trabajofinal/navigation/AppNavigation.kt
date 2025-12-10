package com.inf251.trabajofinal.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.inf251.trabajofinal.screen.BTScreen
import com.inf251.trabajofinal.screen.CameraScreen
import com.inf251.trabajofinal.screen.FirstScreen
import com.inf251.trabajofinal.screen.MicScreen
import com.inf251.trabajofinal.screen.PantallaScreen
import com.inf251.trabajofinal.screen.SpeakerScreen
import com.inf251.trabajofinal.screen.WifiScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = AppScreen.FirstScreen.route) {
        composable(AppScreen.FirstScreen.route){
            FirstScreen(navController)
        }
        composable (AppScreen.WifiScreen.route){
            WifiScreen(navController)
        }
        composable (AppScreen.BTScreen.route){
            BTScreen(navController)
        }
        composable (AppScreen.MicScreen.route){
            MicScreen(navController)
        }
        composable (AppScreen.PantallaScreen.route){
            PantallaScreen(navController)
        }
        composable (AppScreen.SpeakerScreen.route){
            SpeakerScreen(navController)
        }
        composable (AppScreen.CameraScreen.route){
            CameraScreen(navController)
        }
    }
}