package com.inf251.gestionnotas.navigation

sealed class AppScreens (val route: String) {
    object  DocenteScreen: AppScreens(route = "docente_screen")
    object AsignarScreen: AppScreens(route = "asignar_screen")
    object MateriaScreen: AppScreens(route = "materia_screen")
    object DocenteForm: AppScreens(route = "docente_form")
    object MateriaForm: AppScreens(route = "materia_form")
    object AsignarForm: AppScreens(route = "asignar_form")
    object SemestreScreen: AppScreens(route = "semestre_screen")
}