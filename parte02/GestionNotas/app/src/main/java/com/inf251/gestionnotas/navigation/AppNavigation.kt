package com.inf251.gestionnotas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.inf251.gestionnotas.data.dao.AsignacionDao
import com.inf251.gestionnotas.data.dao.DocenteDao
import com.inf251.gestionnotas.data.dao.DocenteFTS
import com.inf251.gestionnotas.data.dao.MateriaDao
import com.inf251.gestionnotas.data.dao.MateriaFTS
import com.inf251.gestionnotas.data.dao.SemestreDao
import com.inf251.gestionnotas.data.dao.SemestreFTS
import com.inf251.gestionnotas.screens.AsignarForm
import com.inf251.gestionnotas.screens.DocenteScreen
import com.inf251.gestionnotas.screens.AsignarScreen
import com.inf251.gestionnotas.screens.DocenteForm
import com.inf251.gestionnotas.screens.MateriaForm
import com.inf251.gestionnotas.screens.MateriaScreen
import com.inf251.gestionnotas.screens.SemestreScreen

@Composable
fun AppNavigation(
    docenteDao: DocenteDao,
    docenteFTS: DocenteFTS,
    materiaDao: MateriaDao,
    materiaFTS: MateriaFTS,
    semestreDao: SemestreDao,
    semestreFTS: SemestreFTS,
    asignacionDao: AsignacionDao
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.DocenteScreen.route ){
        composable(AppScreens.DocenteScreen.route){
            DocenteScreen(navController,docenteFTS,docenteDao)
        }
        composable(AppScreens.AsignarScreen.route){
            AsignarScreen(navController,asignacionDao)
        }
        composable (AppScreens.MateriaScreen.route){
            MateriaScreen(navController,materiaFTS,materiaDao)
        }
        composable(AppScreens.DocenteForm.route){
            DocenteForm(navController,docenteDao)
        }
        composable(AppScreens.MateriaForm.route){
            MateriaForm(navController,materiaDao)
        }
        composable(AppScreens.AsignarForm.route){
            AsignarForm(navController, asignacionDao,docenteDao,materiaDao,semestreDao)
        }
        composable (AppScreens.SemestreScreen.route )
        { SemestreScreen(navController, semestreDao) }
    }
}