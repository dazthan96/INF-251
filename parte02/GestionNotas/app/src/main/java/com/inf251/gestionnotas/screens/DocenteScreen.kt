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
import com.inf251.gestionnotas.components.ReuseIconButtons
import com.inf251.gestionnotas.components.ReuseOutlineText
import com.inf251.gestionnotas.R
import com.inf251.gestionnotas.components.AddColor
import com.inf251.gestionnotas.components.DeleteColor
import com.inf251.gestionnotas.components.EditColor
import com.inf251.gestionnotas.components.ExitColor
import com.inf251.gestionnotas.components.FormColor
import com.inf251.gestionnotas.components.ListColor
import com.inf251.gestionnotas.components.ReuseBarButton
import com.inf251.gestionnotas.components.SearchColor
import com.inf251.gestionnotas.components.TitleText
import com.inf251.gestionnotas.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocenteScreen(navController: NavController){
    Scaffold (
        topBar = {
            TopAppBar(title = {TitleText("Docente",Color.Black)}, colors = TopAppBarDefaults.topAppBarColors(AddColor))
        },
        bottomBar = {Row(Modifier.fillMaxWidth()){
            ReuseBarButton(R.drawable.docente,"Docente",0.3f,AddColor,Color.White,false) {}

            ReuseBarButton(R.drawable.materia,"Materia",0.5f,AddColor,Color.White,true
            ) {
                navController.navigate(AppScreens.MateriaScreen.route)
            }
            ReuseBarButton(R.drawable.asignar,"Asignar",1.0f,AddColor,Color.White,true
            ) {
                navController.navigate(AppScreens.AsignarScreen.route)
            }
        }
        }

    ){
        innerPadding->DocenteContent(modifier=Modifier.padding(innerPadding))
    }
}


@Composable
fun DocenteContent( modifier: Modifier=Modifier){
    var ciDocente by remember { mutableStateOf("") }
    var paternoDoc by remember { mutableStateOf("") }
    var maternoDoc by remember { mutableStateOf("") }
    var nombreDoc by remember { mutableStateOf("") }
    var carreraDoc by remember {mutableStateOf("")}
    Column (
        modifier = modifier.fillMaxSize()
    ){
        Row (Modifier.fillMaxWidth()){
            Column (
                Modifier.fillMaxWidth(.8f).padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                ReuseOutlineText(ciDocente, {ciDocente = it}, "Carnet de Identidad", true, false, KeyboardType.Text)
                ReuseOutlineText(paternoDoc, {paternoDoc = it}, "Paterno", true, false, KeyboardType.Text)
                ReuseOutlineText(maternoDoc, {maternoDoc = it}, "Materno", true, false, KeyboardType.Text)
                ReuseOutlineText(nombreDoc, {nombreDoc=it}, "Nombre", true, false, KeyboardType.Text)
                ReuseOutlineText(carreraDoc, {carreraDoc=it}, "Carrera", true, false, KeyboardType.Text)
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
