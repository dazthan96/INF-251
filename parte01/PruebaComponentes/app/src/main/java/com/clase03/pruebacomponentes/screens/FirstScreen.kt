package com.clase03.pruebacomponentes.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.clase03.pruebacomponentes.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navController: NavController){
    Scaffold (
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Primera Pantalla")
                }
            )
        }
    ) {innerPadding->
        BodyContent(navController,modifier=Modifier.padding(innerPadding))
    }
}
@Composable
fun BodyContent( navController: NavController,modifier:Modifier=Modifier){
    var text by remember { mutableStateOf("") }
    Column (
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ){
        TextField(
            value = text,
            onValueChange = {text=it},
            label = {Text("Escribe tu nombre")},
            modifier = Modifier.fillMaxWidth(0.8f).padding(16.dp)
        )
        Text("Hola navegacion, primer pantalla")
        Button({
            navController.navigate(AppScreens.SecondScreen.route + "/$text")
        }) {
            Text("Segunda Pantalla")
        }
    }
}
