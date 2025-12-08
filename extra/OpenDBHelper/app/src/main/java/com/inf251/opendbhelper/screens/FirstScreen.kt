package com.inf251.opendbhelper.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inf251.opendbhelper.components.Buttons
import com.inf251.opendbhelper.components.ReuseOutlineText
import com.inf251.opendbhelper.R
import com.inf251.opendbhelper.components.ToastReuse
import com.inf251.opendbhelper.model.Persona
import com.inf251.opendbhelper.navigation.AppScreens
import com.inf251.opendbhelper.repository.PersonaRepository

@Composable
fun FirstScreen(navController: NavController, repository: PersonaRepository){
    var textoBusqueda by remember { mutableStateOf("") }
    var resultados by remember { mutableStateOf<List<Persona>>(emptyList()) }
    val context = LocalContext.current
    Column (
        Modifier.fillMaxSize().padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ReuseOutlineText(
            textoBusqueda,
            {textoBusqueda=it},
            "buscar o ver",
            enable = true,
            readOnly = false,
            size = 0.8f,
            KeyboardType.Text
        )
        Spacer(Modifier.height(10.dp))
        Row (
            Modifier.fillMaxWidth(0.8f),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Buttons(
                "Buscar",
                R.color.colorInfo,
                R.color.white,
                enabled = true
            ) {
                if(textoBusqueda.isBlank()){
                    resultados = repository.obtenerTodos()
                }else{
                    val persona = textoBusqueda.toIntOrNull()?.let{
                        repository.obtener(it)
                    }
                    resultados= if(persona!=null) listOf(persona) else emptyList()

                }
            }
            Buttons(
                "Añadir",
                R.color.colorAdd,
                R.color.white,
                enabled = true
            ) {
                navController.navigate(AppScreens.SecondSreen.route+"/${textoBusqueda}")
            }
            if(resultados.isEmpty()){
                ToastReuse(context, "no se encontraron registros")
            }else{
                LazyColumn {
                    items(resultados.size){index->
                        val persona = resultados[index]
                        Card (
                            Modifier.fillMaxWidth().padding(4.dp),
                            elevation = CardDefaults.cardElevation(4.dp)){
                            Column (Modifier.padding(8.dp)){
                                Text("CI: ${persona.ci}")
                                Text("Nombre: ${persona.nombre}")
                                Text("Apellido: ${persona.apellido}")
                            }
                        }

                    }
                }
            }
        }
    }
}