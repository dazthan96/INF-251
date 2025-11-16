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
import com.inf251.gestionnotas.components.ReuseDropListShort
import com.inf251.gestionnotas.components.ReuseOutlineTextShort
import com.inf251.gestionnotas.components.SearchColor
import com.inf251.gestionnotas.components.TagText
import com.inf251.gestionnotas.components.TitleText
import com.inf251.gestionnotas.components.carreras
import com.inf251.gestionnotas.components.genero
import com.inf251.gestionnotas.components.menciones
import com.inf251.gestionnotas.components.periodos
import com.inf251.gestionnotas.data.dao.AsignacionDao
import com.inf251.gestionnotas.data.pojo.DocMatSemPOJO
import com.inf251.gestionnotas.navigation.AppScreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsignarScreen(navController: NavController,asignacionDao: AsignacionDao){
    Scaffold (
        topBar = {
            TopAppBar(title = {TitleText("Busquedas Generales",Color.Black)}, colors = TopAppBarDefaults.topAppBarColors(AddColor))
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
            innerPadding->AsignarContent(modifier=Modifier.padding(innerPadding),navController,asignacionDao)
    }
}
@Composable
fun AsignarContent(modifier: Modifier=Modifier,navController: NavController,asignacionDao: AsignacionDao){
    val scope = rememberCoroutineScope()
    var ciDocente by remember { mutableStateOf("") }
    var nomDocente by remember { mutableStateOf("") }
    var patDocente by remember { mutableStateOf("") }
    var matDocente by remember { mutableStateOf("") }
    var sexoDocente by remember { mutableStateOf("") }
    var sigMateria by remember { mutableStateOf("") }
    var nomMateria by remember { mutableStateOf("") }
    var menMateria by remember { mutableStateOf("") }
    var anioSem by remember { mutableStateOf("") }
    var periodoSem by remember { mutableStateOf("") }
    var carreraGen by remember { mutableStateOf("") }
    var resultados by remember { mutableStateOf<List<DocMatSemPOJO>>(emptyList()) }
    Column (
        modifier = modifier.fillMaxSize().padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Row (Modifier.fillMaxWidth().padding(3.dp)){
            ReuseOutlineTextShort(ciDocente,{ciDocente=it},"CI Docente", 0.45f,KeyboardType.Number)
            Spacer(Modifier.width(8.dp))
            ReuseOutlineTextShort(nomDocente,{nomDocente=it},"Nombre Docente", 1.0f,KeyboardType.Text)
        }
        Row (Modifier.fillMaxWidth().padding(3.dp)){
            ReuseOutlineTextShort(patDocente,{patDocente=it},"Paterno", 0.45f,KeyboardType.Text)
            Spacer(Modifier.width(8.dp))
            ReuseOutlineTextShort(matDocente,{matDocente=it},"Materno", 1.0f,KeyboardType.Text)

        }

        Row (Modifier.fillMaxWidth().padding(3.dp)){
            ReuseOutlineTextShort(sigMateria,{sigMateria=it},"Sigla Materia", 0.45f,KeyboardType.Text)
            Spacer(Modifier.width(8.dp))
            ReuseOutlineTextShort(nomMateria,{nomMateria=it},"Nombre Materia", 1.0f,KeyboardType.Text)
        }

        Row (Modifier.fillMaxWidth().padding(3.dp)){
            ReuseOutlineTextShort(anioSem,{anioSem=it}, "Año", 0.45f,KeyboardType.Number)
            Spacer(Modifier.width(8.dp))

        }

        Row (Modifier.fillMaxWidth().padding(3.dp)){
            ReuseDropListShort(carreras,carreraGen,"Carrera",0.3f) {carreraGen=it }
            Spacer(Modifier.width(8.dp))
            ReuseDropListShort(periodos,periodoSem,"Periodos",0.5f) { periodoSem=it}
            Spacer(Modifier.width(8.dp))
            ReuseDropListShort(genero,sexoDocente,"Genero",1.0f) { sexoDocente=it}
        }
        ReuseDropListShort(menciones,menMateria,"Mencion",0.9f) {menMateria=it }

        Spacer(Modifier.height(15.dp))
        Row(
            Modifier.fillMaxWidth(0.8f),
            horizontalArrangement = Arrangement.Center
        ){
            ReuseButtons("Gestionar",AddColor,Color.White) {
                navController.navigate(AppScreens.AsignarForm.route)
            }
            Spacer(Modifier.width(15.dp))
            ReuseButtons("Buscar", SearchColor,Color.White) {
                scope.launch {
                    resultados = asignacionDao.buscarGeneral(
                        ciDoc = ciDocente.toNullIfBlank(),
                        nomDoc = nomDocente.toNullIfBlank(),
                        patDoc = patDocente.toNullIfBlank(),
                        matDoc = matDocente.toNullIfBlank(),
                        sexoDoc = sexoDocente.toNullIfBlank(),
                        sigMat = sigMateria.toNullIfBlank(),
                        nomMat = nomMateria.toNullIfBlank(),
                        menMat = menMateria.toNullIfBlank(),
                        carrera = carreraGen.toNullIfBlank(),
                        anioSem = anioSem.toNullIfBlank(),
                        perSem = periodoSem.toNullIfBlank()
                    )
                    ciDocente =""
                    nomDocente =""
                    patDocente =""
                    matDocente =""
                    sexoDocente =""
                    sigMateria =""
                    nomMateria =""
                    menMateria =""
                    anioSem =""
                    periodoSem=""
                    carreraGen =""
                }
            }
        }
        LazyColumn {
            items(resultados){resultado->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
                    colors = CardDefaults.cardColors(Color(0xFFDCDCDC))
                ){
                    Column (Modifier.padding(25.dp)){
                        Row{
                            TagText("Docente: ")
                            ContentText("${resultado.nomDocente} ${resultado.patDocente} ${resultado.matDocente}")
                            Spacer(Modifier.width(8.dp))
                            TagText("CI: ")
                            ContentText(resultado.ciDocente)
                        }
                        Row {
                            TagText("Carrera: ")
                            ContentText(resultado.carrera)
                            Spacer(Modifier.width(8.dp))
                            TagText("Genero: ")
                            ContentText(resultado.sexoDocente)
                        }
                        Row {
                            TagText("Sigla: ")
                            ContentText(resultado.sigMateria)
                            Spacer(Modifier.width(8.dp))
                            TagText("Materia: ")
                            ContentText(resultado.nomMateria)
                        }
                        Row {
                            TagText("Mencion: ")
                            ContentText(resultado.menMateria)
                        }
                        Row {
                            TagText("Semestre: ")
                            ContentText("${resultado.anio}-${resultado.periodo}")
                        }

                    }
                }

            }
        }
    }
}
// En un archivo utils, por ejemplo: StringExtensions.kt
fun String?.toNullIfBlank(): String? {
    return if (this.isNullOrBlank()) null else this
}
