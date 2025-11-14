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
fun AsignarScreen(navController: NavController){
    Scaffold (
        topBar = {
            TopAppBar(title = {TitleText("Gestión",Color.Black)}, colors = TopAppBarDefaults.topAppBarColors(AddColor))
        },
        bottomBar = {
            Row(Modifier.fillMaxWidth()){
                ReuseBarButton(R.drawable.docente,"Docente",0.3f,AddColor,Color.White,true
                ) {
                    navController.navigate(AppScreens.DocenteScreen.route)
                }
                ReuseBarButton(R.drawable.materia,"Materia",0.5f,AddColor,Color.White,true
                ) {
                    navController.navigate(AppScreens.MateriaScreen.route)
                }
                ReuseBarButton(R.drawable.asignar,"Asignar",1.0f,AddColor,Color.White,false
                ) {}

            }
        }

    ){
            innerPadding->AsignarContent(modifier=Modifier.padding(innerPadding))
    }
}
@Composable
fun AsignarContent(modifier: Modifier=Modifier){
    var ciDocente by remember { mutableStateOf("") }
    var codMateria by remember { mutableStateOf("") }
    var semestre by remember { mutableStateOf("") }
    Column(modifier = modifier) {
        Row (Modifier.fillMaxWidth()){
            Column (
                Modifier.fillMaxWidth(0.8f).padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                ReuseOutlineText(
                    ciDocente,
                    { ciDocente = it },
                    "Docente",
                    true,
                    false,
                    KeyboardType.Text
                )
                ReuseOutlineText(
                    codMateria,
                    { codMateria = it },
                    "Materia",
                    true,
                    false,
                    KeyboardType.Text
                )
                ReuseOutlineText(
                    semestre,
                    { semestre = it },
                    "Semestre",
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