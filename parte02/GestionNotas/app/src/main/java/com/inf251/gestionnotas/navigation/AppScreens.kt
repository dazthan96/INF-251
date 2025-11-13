package com.inf251.gestionnotas.navigation

sealed class AppScreens (val route: String) {
    object  DocenteScreen: AppScreens(route = "docente_screen")
    object EstudianteScreen: AppScreens(route = "estudiante_screen")
    object MateriaScreen: AppScreens(route = "materia_screen")
}