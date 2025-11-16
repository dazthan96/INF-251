package com.inf251.gestionnotas.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inf251.gestionnotas.R
import com.inf251.gestionnotas.components.AddColor
import com.inf251.gestionnotas.components.ContentText
import com.inf251.gestionnotas.components.DeleteColor
import com.inf251.gestionnotas.components.EditColor
import com.inf251.gestionnotas.components.ReuseDropList
import com.inf251.gestionnotas.components.ReuseIconButtons
import com.inf251.gestionnotas.components.ReuseOutlineText
import com.inf251.gestionnotas.components.SearchColor
import com.inf251.gestionnotas.components.TagText
import com.inf251.gestionnotas.components.TitleText
import com.inf251.gestionnotas.components.carreras
import com.inf251.gestionnotas.components.menciones
import com.inf251.gestionnotas.data.dao.MateriaDao
import com.inf251.gestionnotas.data.entity.MateriaEntity
import com.inf251.gestionnotas.navigation.AppScreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MateriaForm(navController: NavController, materiaDao: MateriaDao){
    Scaffold (
        topBar = {
            TopAppBar(title = {TitleText("Gestionar Materias",Color.Black)}, colors = TopAppBarDefaults.topAppBarColors(AddColor))
        },
        bottomBar = {
            Row(Modifier.fillMaxWidth().padding(25.dp), horizontalArrangement = Arrangement.Center){
                ReuseIconButtons(R.drawable.left,DeleteColor,Color.White) {
                    navController.navigate(AppScreens.MateriaScreen.route)
                }
            }
        }

    ){
            innerPadding->FormularioMateria(modifier=Modifier.padding(innerPadding),materiaDao)
    }
}

@Composable
fun FormularioMateria(modifier: Modifier=Modifier, materiaDao: MateriaDao){
    val scope = rememberCoroutineScope()
    var siglaMateria by remember{ mutableStateOf("") }
    var nombreMateria by remember{mutableStateOf("")}
    var carreraMateria by remember {mutableStateOf("")}
    var mencionMateria by remember { mutableStateOf("") }
    var materiaEncontrada by remember { mutableStateOf<MateriaEntity?>(null) }
    val context = LocalContext.current
    Column (
        modifier = modifier
    ){
        Row (Modifier.fillMaxWidth()){
            Column (
                Modifier.fillMaxWidth(0.8f).padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally){
                ReuseOutlineText(siglaMateria, {siglaMateria=it}, "Codigo",
                    enable = true,
                    readOnly = false,
                    type = KeyboardType.Text
                )
                ReuseOutlineText(nombreMateria,{nombreMateria=it},"Materia",
                    enable = true,
                    readOnly = false,
                    type = KeyboardType.Text
                )
                Spacer(Modifier.height(7.dp))
                ReuseDropList(carreras,carreraMateria,"Carrera de origen") {carreraMateria=it }
                Spacer(Modifier.height(7.dp))
                ReuseDropList(menciones,mencionMateria,"Mencion a la que pertenece") {mencionMateria=it }
                Spacer(Modifier.height(15.dp))
                materiaEncontrada?.let {
                        materia ->
                    Card (
                        Modifier.fillMaxWidth(),
                        colors= CardDefaults.cardColors(Color(0xFFDCDCDC))
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
            Column (Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ){
                Column {
                    ReuseIconButtons(R.drawable.add, AddColor,Color.White) {
                        scope.launch {
                            if(siglaMateria.isBlank()||nombreMateria.isBlank()||carreraMateria.isBlank()||mencionMateria.isBlank()){
                                Toast.makeText(context, "Por favor,llene todos los campos", Toast.LENGTH_SHORT).show()
                            }else{
                                materiaDao.insertarMat(MateriaEntity(
                                    siglaMat = siglaMateria,
                                    materia = nombreMateria,
                                    carreraMat = carreraMateria,
                                    mencionMat = mencionMateria)
                                )
                                siglaMateria=""
                                nombreMateria=""
                                carreraMateria=""
                                mencionMateria=""
                            }

                        }
                    }
                    ReuseIconButtons(R.drawable.search, SearchColor,Color.White) {
                        scope.launch {
                            val id = siglaMateria
                            if( id!=""){
                                materiaEncontrada = materiaDao.buscarMatSingle(id)
                            }
                        }
                    }
                    ReuseIconButtons(R.drawable.edit, EditColor,Color.White) {
                        scope.launch {
                            val id = siglaMateria
                            if(id!=""){
                                materiaDao.modificarMat(MateriaEntity(
                                    siglaMat = siglaMateria,
                                    materia = nombreMateria,
                                    carreraMat = carreraMateria,
                                    mencionMat = mencionMateria
                                ))
                            }
                        }
                    }
                    ReuseIconButtons(R.drawable.delete, DeleteColor,Color.White) {
                        scope.launch {
                            val id = siglaMateria
                            if(id!=""){
                                materiaDao.aliminarDoc(id)
                                siglaMateria=""
                            }
                        }
                    }

                }

            }
        }
    }

}