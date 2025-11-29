package com.inf251.navegacion.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inf251.navegacion.components.Buttons
import com.inf251.navegacion.components.ReuseDropList
import com.inf251.navegacion.R
import com.inf251.navegacion.components.TitleText
import com.inf251.navegacion.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navController: NavController){
    Scaffold (
        topBar={
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = colorResource(R.color.teal_200),
                    titleContentColor = colorResource(R.color.black)
                ),
                title = {TitleText("Primer Pantalla", R.color.white)}
            )
        }
    ){ innerPadding ->
        FirstScreenContent(navController,modifier=Modifier.padding(innerPadding))
    }
}

@Composable
fun FirstScreenContent(navController: NavController, modifier:Modifier=Modifier){
    var fruta by remember { mutableStateOf("") }
    val frutas = listOf("manzana", "pera", "naranja","piña")
    Column (
        Modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        ReuseDropList(
            options = frutas,
            selectedOption = fruta,
            text = "Escoge tu fruta"
        ) {fruta = it }
        Spacer(Modifier.height(10.dp))
        Buttons(
            "Next Screen",
            R.color.colorList,
            R.color.black
        ) {navController.navigate(AppScreens.SecondScreen.route +"/$fruta") }
    }

}