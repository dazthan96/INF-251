package com.miapp.tarea01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MisActividades()
        }
    }
}
@Composable
fun MisActividades() {
    val navController = rememberNavController() // Controlador de navegación
    NavHost(
        navController = navController, // Pasamos el controlador
        startDestination = "pantalla1" // Pantalla inicial
    ) {
        composable("pantalla1") { Pantalla1(navController) } // Primera pantalla
        composable("pantalla2") { Pantalla2(navController) } // Segunda pantalla
        composable("pantalla3") { Pantalla3(navController) } // Tercera pantalla
        composable("pantalla4") { Pantalla4(navController) } // Tercera pantalla
    }
}
@Composable
fun Pantalla1(navController: NavHostController) {
    Column (Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Spacer (modifier = Modifier.padding(top = 40.dp))
        Text("CARTELERA", fontWeight = FontWeight.Bold, fontSize = 50.sp, fontStyle = FontStyle.Italic)
        Spacer (modifier = Modifier.padding(top = 40.dp))
        Button(
            onClick = { navController.navigate("pantalla2") },Modifier.width(200.dp).height(75.dp)
        ) {
            Text("Superman")
        }
        Spacer(Modifier.height(25.dp))
        Button(
            onClick = { navController.navigate("pantalla3") },Modifier.width(200.dp).height(75.dp)
        ) {
            Text("Fantasticos")
        }
        Spacer(Modifier.height(25.dp))
        Button(
            onClick = { navController.navigate("pantalla4") },Modifier.width(200.dp).height(75.dp)
        ) {
            Text("Shreck")
        }
    }
}
// Segunda pantalla con botones para avanzar y retroceder
@Composable
fun Pantalla2(navController: NavHostController) {
    Column (Modifier.fillMaxSize().padding(0.dp).background(color = Color.Red), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Text("Superman", fontWeight = FontWeight.Bold, fontSize = 35.sp)
        Spacer(Modifier.height(25.dp))
        Image(painterResource(R.drawable.superman), contentDescription = "", Modifier.height(250.dp), contentScale = ContentScale.Crop)
        Spacer(Modifier.height(25.dp))
        Button(
            onClick = { navController.popBackStack() },Modifier.width(200.dp) .height(75.dp)// Retrocede a pantalla 2
        ) {
            Text("Volver")
        }
    }
}
@Composable
fun Pantalla3(navController: NavHostController) {
    Column (Modifier.fillMaxSize().padding(0.dp).background(color = Color.Blue), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Text("Los 4 Fantasticos", fontWeight = FontWeight.Bold, fontSize = 35.sp)
        Spacer(Modifier.height(25.dp))
        Image(painterResource(R.drawable.fantastic4), contentDescription = "", Modifier.height(250.dp), contentScale = ContentScale.Crop)
        Spacer(Modifier.height(25.dp))
        Button(
            onClick = { navController.popBackStack() } ,Modifier.width(200.dp).height(75.dp)// Retrocede a pantalla 2
        ) {
            Text("Volver")
        }
    }
}
@Composable
fun Pantalla4(navController: NavHostController) {
    Column (Modifier.fillMaxSize().padding(0.dp).background(color = Color.Green), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Text("Shreck", fontWeight = FontWeight.Bold, fontSize = 35.sp)
        Spacer(Modifier.height(25.dp))
        Image(painterResource(R.drawable.shrek), contentDescription = "", Modifier.height(250.dp), contentScale = ContentScale.Crop)
        Spacer(Modifier.height(25.dp))
        Button(
            onClick = { navController.popBackStack() } ,Modifier.width(200.dp).height(75.dp)// Retrocede a pantalla 2
        ) {
            Text("Volver")
        }
    }
}
