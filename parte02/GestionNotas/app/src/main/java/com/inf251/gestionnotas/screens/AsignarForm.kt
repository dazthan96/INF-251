package com.inf251.gestionnotas.screens

import android.widget.Toast
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
import com.inf251.gestionnotas.components.DeleteColor
import com.inf251.gestionnotas.components.EditColor
import com.inf251.gestionnotas.components.ReuseIconButtons
import com.inf251.gestionnotas.components.ReuseOutlineText
import com.inf251.gestionnotas.components.SearchColor
import com.inf251.gestionnotas.components.TitleText
import com.inf251.gestionnotas.components.semestreActual
import com.inf251.gestionnotas.data.dao.AsignacionDao
import com.inf251.gestionnotas.data.dao.DocenteDao
import com.inf251.gestionnotas.data.dao.MateriaDao
import com.inf251.gestionnotas.data.dao.SemestreDao
import com.inf251.gestionnotas.data.entity.AsignacionEntity
import com.inf251.gestionnotas.navigation.AppScreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsignarForm(navController: NavController,asignacionDao: AsignacionDao,docenteDao: DocenteDao,materiaDao: MateriaDao,semestreDao: SemestreDao){
    Scaffold (
        topBar = {
            TopAppBar(title = {TitleText("Gestionar Asignaciones",Color.Black)}, colors = TopAppBarDefaults.topAppBarColors(AddColor))
        },
        bottomBar = {
            Row(Modifier.fillMaxWidth().padding(25.dp), horizontalArrangement = Arrangement.Center){
                ReuseIconButtons(R.drawable.left,DeleteColor,Color.White) {
                    navController.navigate(AppScreens.AsignarScreen.route)
                }
                ReuseIconButtons(R.drawable.calendar,SearchColor,Color.White) {
                    navController.navigate(AppScreens.SemestreScreen.route)
                }
            }
        }

    ){
            innerPadding->FormularioAsignar(modifier=Modifier.padding(innerPadding),asignacionDao,docenteDao,materiaDao,semestreDao)
    }
}

@Composable
fun FormularioAsignar(modifier: Modifier=Modifier, asignacionDao: AsignacionDao,docenteDao: DocenteDao,materiaDao: MateriaDao,semestreDao: SemestreDao){
    var ciDocente by remember { mutableStateOf("") }
    var codMateria by remember { mutableStateOf("") }
    val semestre = semestreActual
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
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
                    { },
                    "Semestre",
                    true,
                    true,
                    KeyboardType.Text
                )

            }
            Column (Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ){
                Column {
                    ReuseIconButtons(R.drawable.add, AddColor,Color.White) {
                        scope.launch {
                            val docente = docenteDao.buscarCarnetDoc(ciDocente)
                            val materia = materiaDao.buscarMatSingle(codMateria)
                            val semestrep = semestreDao.buscarSemestre(semestre)
                            if(docente==null || materia==null || semestrep==null){
                                Toast.makeText(context, "Asegurese que las entidades existan", Toast.LENGTH_SHORT).show()
                            }else{
                                asignacionDao.insertAsig(AsignacionEntity(
                                    ciDoc = ciDocente.trim().lowercase(),
                                    sigMat = codMateria.trim().lowercase(),
                                    idSem = semestre
                                ))
                                ciDocente=""
                                codMateria=""
                            }

                        }
                    }

                    ReuseIconButtons(R.drawable.edit, EditColor,Color.White) {
                        scope.launch {
                            asignacionDao.updateAsig(AsignacionEntity(
                                ciDoc = ciDocente,
                                sigMat = codMateria,
                                idSem = semestre
                            ))
                        }
                    }
                    ReuseIconButtons(R.drawable.delete, DeleteColor,Color.White) {
                        scope.launch {
                            asignacionDao.deleteAsig(
                                ciDoc = ciDocente,
                                siglaMat = codMateria,
                                semestre = semestre
                            )
                            ciDocente=""
                            codMateria=""
                        }

                    }

                }
            }
        }
    }
}