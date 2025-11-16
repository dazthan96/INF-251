package com.inf251.gestionnotas.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inf251.gestionnotas.components.ReuseOutlineText
import com.inf251.gestionnotas.R
import com.inf251.gestionnotas.components.AddColor
import com.inf251.gestionnotas.components.ContentText
import com.inf251.gestionnotas.components.ReuseBarButton
import com.inf251.gestionnotas.components.ReuseButtons
import com.inf251.gestionnotas.components.SearchColor
import com.inf251.gestionnotas.components.TagText
import com.inf251.gestionnotas.components.TitleText
import com.inf251.gestionnotas.data.dao.DocenteDao
import com.inf251.gestionnotas.data.dao.DocenteFTS
import com.inf251.gestionnotas.data.entity.DocenteEntity
import com.inf251.gestionnotas.navigation.AppScreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocenteScreen(navController: NavController,docenteFTS: DocenteFTS,docenteDao: DocenteDao){
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
        innerPadding->DocenteContent(modifier=Modifier.padding(innerPadding),navController,docenteFTS,docenteDao)
    }
}


@Composable
fun DocenteContent( modifier: Modifier=Modifier, navController: NavController,docenteFTS: DocenteFTS,docenteDao: DocenteDao){
    val scope = rememberCoroutineScope()
    var param by remember { mutableStateOf("") }
    var docentes by remember { mutableStateOf<List<DocenteEntity>>(emptyList()) }
    Column (
        modifier = modifier.fillMaxSize().padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ReuseOutlineText(param, {param=it},"Buscar en Docentes",true,false, KeyboardType.Text)
        Row(
            Modifier.fillMaxWidth(0.8f),
            horizontalArrangement = Arrangement.Center
        ){
            ReuseButtons("Gestionar",AddColor,Color.White) {
                navController.navigate(AppScreens.DocenteForm.route)
            }
            Spacer(Modifier.width(15.dp))
            ReuseButtons("Buscar", SearchColor,Color.White) {
                scope.launch {
                    docentes = if(param.isBlank()){
                        docenteDao.listarDocN()
                    }else{
                        docenteFTS.buscarDocFts(param)
                    }
                }
            }
        }
        Spacer(Modifier.height(25.dp))
        LazyColumn {
            items(docentes){docente->
                Card (
                    Modifier.fillMaxWidth().padding(vertical = 5.dp),
                    colors= CardDefaults.cardColors(Color(0xFFDCDCDC))
                ){
                    Column (Modifier.padding(25.dp)){
                        Row {
                            TagText("Carnet de Identidad: ")
                            ContentText(docente.carnetDoc)
                        }
                        Row {
                            TagText("Nombre Docente: ")
                            ContentText("${docente.paterno} ${docente.materno}, ${docente.nombre}")
                        }
                        Row {
                            TagText("Carrera Origen: ")
                            ContentText("${docente.carrera}")
                        }
                        Row {
                            TagText("Sexo: ")
                            ContentText("${docente.sexo}")
                        }

                    }
                }
            }
        }
    }

}
