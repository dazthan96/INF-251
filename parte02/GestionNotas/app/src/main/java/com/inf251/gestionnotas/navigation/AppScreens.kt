package com.inf251.gestionnotas.navigation

sealed class AppScreens (val route: String) {
    object  DocenteScreen: AppScreens(route = "docente_screen")
    object AsignarScreen: AppScreens(route = "asignar_screen")
    object MateriaScreen: AppScreens(route = "materia_screen")
}