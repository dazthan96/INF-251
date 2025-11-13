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
import androidx.compose.ui.tooling.preview.Preview
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
fun EstudianteScreen(navController: NavController){
    Scaffold (
        topBar = {
            TopAppBar(title = {TitleText("Estudiante",Color.Black)}, colors = TopAppBarDefaults.topAppBarColors(AddColor))
        },
        bottomBar = {
            Row(Modifier.fillMaxWidth()){
                ReuseBarButton(R.drawable.docente,"Docente",0.3f,AddColor,Color.White,true
                ) {
                    navController.navigate(AppScreens.DocenteScreen.route)
                }
                ReuseBarButton(R.drawable.estudiante,"Estudiante",0.5f,AddColor,Color.White,false
                ) {}
                ReuseBarButton(R.drawable.materia,"Materia",1.0f,AddColor,Color.White,true
                ) {
                    navController.navigate(AppScreens.MateriaScreen.route)
                }
            }
        }

    ){
            innerPadding->EstudianteContent(modifier=Modifier.padding(innerPadding))
    }
}
@Composable
fun EstudianteContent(modifier: Modifier=Modifier){
    var ciEstudiante by remember { mutableStateOf("") }
    var nombreEstudiante by remember { mutableStateOf("") }
    var paternoEstudiante by remember { mutableStateOf("") }
    var maternoEstudiante by remember {mutableStateOf("")  }
    Column(modifier = modifier) {
        Row (Modifier.fillMaxWidth()){
            Column (
                Modifier.fillMaxWidth(0.8f).padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                ReuseOutlineText(
                    ciEstudiante,
                    { ciEstudiante = it },
                    "Carnet de Identidad",
                    true,
                    false,
                    KeyboardType.Text
                )
                ReuseOutlineText(
                    paternoEstudiante,
                    { paternoEstudiante = it },
                    "Paterno",
                    true,
                    false,
                    KeyboardType.Text
                )
                ReuseOutlineText(
                    maternoEstudiante,
                    { maternoEstudiante = it },
                    "Materno",
                    true,
                    false,
                    KeyboardType.Text
                )
                ReuseOutlineText(
                    nombreEstudiante,
                    { nombreEstudiante = it },
                    "Nombre",
                    true,
                    false,
                    KeyboardType.Text
                )
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