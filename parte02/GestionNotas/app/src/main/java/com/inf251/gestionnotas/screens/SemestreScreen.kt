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
import androidx.compose.foundation.layout.width
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
import com.inf251.gestionnotas.components.ReuseButtons
import com.inf251.gestionnotas.components.ReuseDropList
import com.inf251.gestionnotas.components.ReuseIconButtons
import com.inf251.gestionnotas.components.ReuseOutlineText
import com.inf251.gestionnotas.components.TitleText
import com.inf251.gestionnotas.components.getAnioActual
import com.inf251.gestionnotas.components.periodos
import com.inf251.gestionnotas.data.dao.SemestreDao
import com.inf251.gestionnotas.data.entity.SemestreEntity
import com.inf251.gestionnotas.navigation.AppScreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SemestreScreen(navController: NavController, semestreDao: SemestreDao){
    Scaffold (
        topBar = {
            TopAppBar(title = {TitleText("Agregar Gestión",Color.Black)}, colors = TopAppBarDefaults.topAppBarColors(AddColor))
        },
        bottomBar = {
            Row(Modifier.fillMaxWidth().padding(25.dp), horizontalArrangement = Arrangement.Center){
                ReuseIconButtons(R.drawable.left,DeleteColor,Color.White) {
                    navController.navigate(AppScreens.AsignarForm.route)
                }
            }
        }

    ){
            innerPadding->SemestreContent(modifier=Modifier.padding(innerPadding),semestreDao)
    }
}
@Composable
fun SemestreContent(modifier: Modifier=Modifier,semestreDao: SemestreDao){
    var anio by remember { mutableStateOf("") }
    var periodo by remember { mutableStateOf("") }
    var idSemestre by remember { mutableStateOf("") }
    val context= LocalContext.current
    val scope = rememberCoroutineScope()
    idSemestre = if(anio.isNotEmpty()){
        "$anio-$periodo"
    }else{
        ""
    }


    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        ReuseOutlineText(idSemestre,{idSemestre=it},"Semestre",
            enable = true,
            readOnly = true,
            type = KeyboardType.Number
        )
        ReuseOutlineText(
            anio,
            { anio = it },
            "Año",
            true,
            readOnly = false,
            type = KeyboardType.Number
        )
        Spacer(Modifier.height(7.dp))
        ReuseDropList(periodos,periodo,"Periodo a elegir") {periodo=it }
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            ReuseButtons("Agregar", AddColor,Color.White) {
                scope.launch {
                    val anioInt = anio.toIntOrNull()
                    val currentYear = getAnioActual()
                    if(anioInt== null || anioInt<2000 ||anioInt>currentYear){
                        Toast.makeText(context, "Año inválido", Toast.LENGTH_SHORT).show()
                    }else if(periodo.isBlank()){
                        Toast.makeText(context, "Periodo Requerido", Toast.LENGTH_SHORT).show()
                    }else{
                        semestreDao.insertarSem(SemestreEntity(
                            idSemestre = idSemestre,
                            anioSem = anio,
                            periodoSem = periodo
                        ))
                        idSemestre=""
                        anio=""
                        periodo=""
                    }

                }
            }
            Spacer(Modifier.width(15.dp))
            ReuseButtons("Editar",EditColor,Color.White) {
                scope.launch {
                    if(anio.isBlank()||periodo.isBlank()){
                        Toast.makeText(context, "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show()
                    }else{
                        semestreDao.modificarMat(SemestreEntity(
                            idSemestre = idSemestre,
                            anioSem = anio,
                            periodoSem = periodo
                        ))
                        idSemestre=""
                        anio=""
                    }

                }
            }
        }
    }

}