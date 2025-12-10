package com.inf251.trabajofinal.navigation

sealed class AppScreen (val route: String){
    object FirstScreen: AppScreen(route = "first_screen")
    object BTScreen: AppScreen(route = "b_t_screen")
    object CameraScreen: AppScreen(route = "camera_screen")
    object MicScreen: AppScreen(route = "mic_screen")
    object PantallaScreen: AppScreen(route = "pantalla_screen")
    object SpeakerScreen: AppScreen(route = "speaker_screen")
    object WifiScreen: AppScreen(route = "wifi_screen")
}