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
import com.inf251.gestionnotas.R
import com.inf251.gestionnotas.components.AddColor
import com.inf251.gestionnotas.components.ContentText
import com.inf251.gestionnotas.components.ReuseBarButton
import com.inf251.gestionnotas.components.ReuseButtons
import com.inf251.gestionnotas.components.ReuseOutlineText
import com.inf251.gestionnotas.components.SearchColor
import com.inf251.gestionnotas.components.TagText
import com.inf251.gestionnotas.components.TitleText
import com.inf251.gestionnotas.data.dao.MateriaDao
import com.inf251.gestionnotas.data.dao.MateriaFTS
import com.inf251.gestionnotas.data.entity.MateriaEntity
import com.inf251.gestionnotas.navigation.AppScreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MateriaScreen(navController: NavController, materiaFTS: MateriaFTS,materiaDao: MateriaDao){
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
                ReuseBarButton(R.drawable.materia,"Materia",0.5f,AddColor,Color.White,false) {}
                ReuseBarButton(R.drawable.asignar,"Asignar",1.0f,AddColor,Color.White,true
                ) {
                    navController.navigate(AppScreens.AsignarScreen.route)
                }

            }
        }

    ){
            innerPadding->MateriaContent(modifier=Modifier.padding(innerPadding),navController,materiaFTS,materiaDao)
    }
}

@Composable
fun MateriaContent(modifier: Modifier=Modifier,navController: NavController,materiaFTS: MateriaFTS,materiaDao: MateriaDao){
    val scope = rememberCoroutineScope()
    var param by remember { mutableStateOf("") }
    var materias by remember { mutableStateOf<List<MateriaEntity>>(emptyList()) }
    Column (
        modifier = modifier.fillMaxSize().padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ReuseOutlineText(param.lowercase(), {param=it},"Buscar en Materias",
            enable = true,
            readOnly = false,
            type = KeyboardType.Text
        )
        Row(
            Modifier.fillMaxWidth(0.8f),
            horizontalArrangement = Arrangement.Center
        ){
            ReuseButtons("Gestionar",AddColor,Color.White) {
                navController.navigate(AppScreens.MateriaForm.route)
            }
            Spacer(Modifier.width(15.dp))
            ReuseButtons("Buscar", SearchColor,Color.White) {
                scope.launch {
                    materias = if(param.isBlank()){
                        materiaDao.listarMatN()
                    }else{
                        materiaFTS.buscarMFts(param)
                    }
                }
            }
        }
        Spacer(Modifier.height(25.dp))
        LazyColumn {
            items(materias){materia->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
                    colors = CardDefaults.cardColors(Color(0xFFDCDCDC))
                ){
                    Column (Modifier.padding(25.dp)){
                        Row {
                            TagText("Sigla de Materia: ")
                            ContentText(materia.siglaMat)
                        }
                        Row {
                            TagText("Nombre de Materia: ")
                            ContentText("${materia.materia}")
                        }
                        Row {
                            TagText("Carrera Origen: ")
                            ContentText("${materia.carreraMat}")
                        }
                        Row {
                            TagText("Mencion: ")
                            ContentText("${materia.mencionMat}")
                        }

                    }
                }

            }
        }
    }
}
