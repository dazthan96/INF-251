package com.inf251.gestionnotas.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inf251.gestionnotas.R
import com.inf251.gestionnotas.components.AddColor
import com.inf251.gestionnotas.components.DeleteColor
import com.inf251.gestionnotas.components.EditColor
import com.inf251.gestionnotas.components.ExitColor
import com.inf251.gestionnotas.components.FormColor
import com.inf251.gestionnotas.components.ListColor
import com.inf251.gestionnotas.components.ReuseBarButton
import com.inf251.gestionnotas.components.ReuseIconButtons
import com.inf251.gestionnotas.components.ReuseOutlineText
import com.inf251.gestionnotas.components.SearchColor
import com.inf251.gestionnotas.components.TitleText
import com.inf251.gestionnotas.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MateriaScreen(navController: NavController){
    Scaffold (
        topBar = {
            TopAppBar(title = {TitleText("Materia",Color.Black)}, colors = TopAppBarDefaults.topAppBarColors(AddColor))
        },
        bottomBar = {
            Row(Modifier.fillMaxWidth()){
                ReuseBarButton(R.drawable.docente,"Docente",0.3f,AddColor,Color.White,true
                ) {
                    navController.navigate(AppScreens.DocenteScreen.route)
                }
                ReuseBarButton(R.drawable.estudiante,"Estudiante",0.5f,AddColor,Color.White,true
                ) {
                    navController.navigate(AppScreens.EstudianteScreen.route)
                }
                ReuseBarButton(R.drawable.materia,"Materia",1.0f,AddColor,Color.White,false) {}
            }
        }

    ){
            innerPadding->MateriaContent(modifier=Modifier.padding(innerPadding))
    }
}

@Composable
fun MateriaContent(modifier: Modifier=Modifier){
    var codigoMateria by remember{ mutableStateOf("") }
    var nombreMateria by remember{mutableStateOf("")}
    var creditsMateria by remember { mutableStateOf("") }
    var carreraMateria by remember {mutableStateOf("")}
    var mencionMateria by remember { mutableStateOf("") }
    Column (
        modifier = modifier
    ){
        Row (Modifier.fillMaxWidth()){
            Column (
                Modifier.fillMaxWidth(0.8f).padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally){
                ReuseOutlineText(codigoMateria, {codigoMateria=it}, "Codigo", true, false, KeyboardType.Text)
                ReuseOutlineText(nombreMateria,{nombreMateria=it},"Materia", true, false, KeyboardType.Text)
                ReuseOutlineText(creditsMateria,{creditsMateria=it}, "Creditos", true, false,KeyboardType.Number)
                ReuseOutlineText(carreraMateria, {carreraMateria=it}, "Carrera", true, false, KeyboardType.Text)
                ReuseOutlineText(mencionMateria, {mencionMateria=it}, "Mencion", true, false,KeyboardType.Text)
            }
            Column (Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ){
                Column {
                    ReuseIconButtons(R.drawable.add, AddColor,Color.White,{})
                    ReuseIconButtons(R.drawable.search, SearchColor,Color.White,{})
                    ReuseIconButtons(R.drawable.edit, EditColor,Color.White,{})
                    ReuseIconButtons(R.drawable.delete, DeleteColor,Color.White,{})
                    ReuseIconButtons(R.drawable.list, ListColor,Color.White,{})
                }
                Column {
                    ReuseIconButtons(R.drawable.nota, FormColor,Color.White,{})
                    ReuseIconButtons(R.drawable.exit, ExitColor,Color.White,{})
                }
            }
        }
    }

}