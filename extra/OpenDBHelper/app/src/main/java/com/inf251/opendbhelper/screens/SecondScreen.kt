package com.inf251.opendbhelper.screens

import android.annotation.SuppressLint
import android.app.appsearch.AppSearchSchema
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inf251.opendbhelper.R
import com.inf251.opendbhelper.components.Buttons
import com.inf251.opendbhelper.components.ReuseOutlineText
import com.inf251.opendbhelper.model.Persona
import com.inf251.opendbhelper.navigation.AppScreens
import com.inf251.opendbhelper.repository.PersonaRepository

@Composable
fun SecondScreen(navController: NavController, p_ci:String?, repository: PersonaRepository){
    var ci by remember { mutableStateOf("") }
    var nombre by remember{mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    val camposHabilitados = p_ci==null
    val botonActualizarHabilitado = p_ci==null
    val botonCrearHablitado = p_ci!=null

    LaunchedEffect(p_ci) {
        p_ci?.toIntOrNull()?.let{ciInt->
            val persona = repository.obtener(ciInt)
            persona?.let{
                ci = it.ci.toString()
                nombre = it.nombre
                apellido = it.apellido
            }
        }
    }
    Column(Modifier.padding(16.dp)){
        ReuseOutlineText(
            ci,
            {ci=it},
            "Carnet de Identidad",
            !camposHabilitados,
            camposHabilitados,
            0.8f,
            KeyboardType.Text
        )
        ReuseOutlineText(
            nombre,
            {nombre=it},
            "Nombre",
            !camposHabilitados,
            camposHabilitados,
            0.8f,
            KeyboardType.Text

        )
        ReuseOutlineText(
            apellido,
            {apellido=it},
            "Apellido",
            !camposHabilitados,
            camposHabilitados,
            0.8f,
            KeyboardType.Text

        )
        Spacer(Modifier.padding(8.dp))
        Column (Modifier.fillMaxWidth().padding(8.dp)){
            Buttons(
                "Añadir",
                R.color.colorAdd,
                R.color.white,
                botonCrearHablitado
            ) {
                repository.insertar(Persona(ci.toInt(), nombre, apellido))
                navController.navigate(AppScreens.FirstScreen.route)
            }
            Buttons(
                "Actualizar",
                R.color.colorWarning,
                R.color.white,
                botonActualizarHabilitado
            ) {
                repository.actualizar(Persona(ci.toInt(), nombre, apellido))
                navController.navigate(AppScreens.FirstScreen.route)
            }
            Buttons(
                "Retroceder",
                R.color.colorDelete,
                R.color.white,
                true
            ) {
                navController.navigate(AppScreens.FirstScreen.route)
            }
        }
    }

}