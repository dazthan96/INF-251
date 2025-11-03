package com.clase03.pruebacomponentes.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.clase03.pruebacomponentes.navigation.AppScreens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavController, text:String?){
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
    ){innerPadding->
        SecondBodyContent(navController,Modifier.padding(innerPadding), text)
    }
}
@Composable
fun SecondBodyContent(navController: NavController,modifier:Modifier=Modifier, text:String?){
    Column (
        modifier=modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ){
        Text("Esto es android Studio, hola $text")

        Button({
            navController.navigate(AppScreens.FirstScreen.route)
        }) {
            Text("Primer Pantalla")
        }
    }
}
