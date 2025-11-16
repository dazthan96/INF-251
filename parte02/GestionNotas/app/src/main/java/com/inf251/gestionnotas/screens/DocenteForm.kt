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
import androidx.compose.ui.autofill.ContentType
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
import com.inf251.gestionnotas.components.genero
import com.inf251.gestionnotas.data.dao.DocenteDao
import com.inf251.gestionnotas.data.entity.DocenteEntity
import com.inf251.gestionnotas.navigation.AppScreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocenteForm(navController: NavController, docenteDao: DocenteDao){
    Scaffold (
        topBar = {
            TopAppBar(title = {TitleText("Gestionar Docentes",Color.Black)}, colors = TopAppBarDefaults.topAppBarColors(AddColor))
        },
        bottomBar = {
            Row(Modifier.fillMaxWidth().padding(25.dp), horizontalArrangement = Arrangement.Center){
                ReuseIconButtons(R.drawable.left,DeleteColor,Color.White) {
                    navController.navigate(AppScreens.DocenteScreen.route)
                }
            }
        }

    ){
            innerPadding->FormularioDocente(modifier=Modifier.padding(innerPadding),docenteDao)
    }
}

@Composable
fun FormularioDocente( modifier: Modifier=Modifier,docenteDao: DocenteDao){
    val scope = rememberCoroutineScope ()
    var ciDocente by remember { mutableStateOf("") }
    var paternoDoc by remember { mutableStateOf("") }
    var maternoDoc by remember { mutableStateOf("") }
    var nombreDoc by remember { mutableStateOf("") }
    var carreraDoc by remember {mutableStateOf("")}
    var sexoDoc by remember { mutableStateOf("") }
    var docenteEncontrado by remember {mutableStateOf<DocenteEntity?>(null) }
    val context = LocalContext.current
    Column (
        modifier = modifier.fillMaxSize()
    ){
        Row (Modifier.fillMaxWidth()){
            Column (
                Modifier.fillMaxWidth(.8f).padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                ReuseOutlineText(ciDocente, {ciDocente = it}, "Carnet de Identidad",
                    enable = true,
                    readOnly = false,
                    type = KeyboardType.Number
                )
                ReuseOutlineText(paternoDoc, {paternoDoc = it}, "Paterno",
                    enable = true,
                    readOnly = false,
                    type = KeyboardType.Text
                )
                ReuseOutlineText(maternoDoc, {maternoDoc = it}, "Materno",
                    enable = true,
                    readOnly = false,
                    type = KeyboardType.Text
                )
                ReuseOutlineText(nombreDoc, {nombreDoc=it}, "Nombre",
                    enable = true,
                    readOnly = false,
                    type = KeyboardType.Text
                )
                Spacer(Modifier.height(7.dp))
                ReuseDropList(carreras,carreraDoc,"Carrera de origen") {carreraDoc=it }
                Spacer(Modifier.height(7.dp))
                ReuseDropList(genero,sexoDoc,"Elija su genero") {sexoDoc=it }
                Spacer(Modifier.height(15.dp))
                docenteEncontrado?.let {
                        docente ->
                    Card (
                        Modifier.fillMaxWidth(),
                        colors= CardDefaults.cardColors(Color(0xFFDCDCDC))
                    ){
                        Column (Modifier.padding(25.dp)){
                            Row {
                                TagText("Carnet de Identidad: ")
                                ContentText(docente.carnetDoc)
                            }
                            Row {
                                TagText("Nombre: ")
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
            Column (Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ){
                Column {
                    ReuseIconButtons(R.drawable.add, AddColor,Color.White) {
                        scope.launch {
                            if(ciDocente.isBlank()||paternoDoc.isBlank()||maternoDoc.isBlank()||nombreDoc.isBlank()||carreraDoc.isBlank()||sexoDoc.isBlank()){
                                Toast.makeText(context, "Por favor, llenar todos los campos", Toast.LENGTH_SHORT).show()
                            }else{
                                docenteDao.insertarDoc(DocenteEntity(
                                    carnetDoc = ciDocente,
                                    paterno = paternoDoc,
                                    materno = maternoDoc,
                                    nombre = nombreDoc,
                                    carrera = carreraDoc,
                                    sexo = sexoDoc)
                                )
                                ciDocente=""
                                paternoDoc=""
                                maternoDoc=""
                                nombreDoc=""
                                carreraDoc=""
                                sexoDoc=""
                            }

                        }
                    }
                    ReuseIconButtons(R.drawable.search, SearchColor,Color.White) {
                        scope.launch {
                            val carnet = ciDocente
                            if(carnet!=""){
                                docenteEncontrado = docenteDao.buscarCarnetDoc(carnet)
                            }
                        }
                    }
                    ReuseIconButtons(R.drawable.edit, EditColor,Color.White) {
                        scope.launch {
                            val carnet = ciDocente
                            if(carnet !=""){
                                docenteDao.modificarDoc(DocenteEntity(
                                    carnetDoc = carnet,
                                    paterno = paternoDoc,
                                    materno = maternoDoc,
                                    nombre = nombreDoc,
                                    carrera = carreraDoc,
                                    sexo = sexoDoc)
                                )
                            }
                        }
                    }
                    ReuseIconButtons(R.drawable.delete, DeleteColor,Color.White) {
                        scope.launch {
                            val carnet = ciDocente
                            if(carnet!=""){
                                docenteDao.aliminarDoc(carnet)
                                ciDocente=""
                            }

                        }
                    }
                }
            }
        }
    }

}