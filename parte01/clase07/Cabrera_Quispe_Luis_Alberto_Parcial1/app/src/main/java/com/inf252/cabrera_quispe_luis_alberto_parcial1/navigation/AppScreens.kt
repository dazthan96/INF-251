package com.inf252.cabrera_quispe_luis_alberto_parcial1.navigation

sealed class AppScreens(val route:String) {
    object FirstScreen: AppScreens(route = "first_screen")
    object SecondScreen: AppScreens(route = "second_screen")
    object ThirdScreen : AppScreens(route = "third_screen")
}

//si hay mas paginas, debemos agregar mas object asegurando que las pantallas existan