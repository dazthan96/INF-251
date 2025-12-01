package com.inf251.examen2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inf251.examen2.components.Buttons
import com.inf251.examen2.components.TitleText
import com.inf251.examen2.R
import com.inf251.examen2.navigation.AppScreens

@Composable
fun FirstScreen(navController: NavController){
    Column (Modifier.fillMaxSize()){
        Box (Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.primaryColor))){
            TitleText(
                text = "Primer pantalla",
                color = R.color.titleColor)
        }
        Spacer(Modifier.height(10.dp))
        Row (
            Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically){
            Buttons(
                text = "Pantalla 2",
                colorButton = R.color.teal_200,
                colorContent = R.color.white,
                enabled = true
            ) {
                navController.navigate(AppScreens.SecondScreen.route)
            }
            Spacer(Modifier.width(5.dp))
            Buttons(
                text = "Pantalla 3",
                colorButton = R.color.teal_700,
                colorContent = R.color.white,
                enabled = true
            ) {
                navController.navigate(AppScreens.ThirdScreen.route)
            }
            Spacer(Modifier.width(5.dp))
            Buttons(
                text = "Camara",
                colorButton = R.color.teal_700,
                colorContent = R.color.white,
                enabled = true
            ) {
                navController.navigate(AppScreens.CameraScreen.route)
            }
        }
    }
}
