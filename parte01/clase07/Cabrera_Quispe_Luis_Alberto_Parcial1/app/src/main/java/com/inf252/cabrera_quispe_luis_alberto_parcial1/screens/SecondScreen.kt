package com.inf252.cabrera_quispe_luis_alberto_parcial1.screens

//import com.inf252.cabrera_quispe_luis_alberto_parcial1.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inf252.cabrera_quispe_luis_alberto_parcial1.navigation.AppNavigation
import com.inf252.cabrera_quispe_luis_alberto_parcial1.funtions.repetirVocales

//en estas pantallas simplemente escribimos como si se estuviera en la app principal
@Composable
fun SecondScreen(navController: NavController, text: String?){
    var posX by remember { mutableIntStateOf(0) }
    var posY by remember { mutableIntStateOf(0) }
    val textAux = repetirVocales("$text")
    Column (
        Modifier.fillMaxSize(),

    ){
        Text(
            textAux,
            Modifier.offset(posX.dp, posY.dp)
        )

    }
}

@Preview(showSystemUi = true)
@Composable
fun Prev2App(){
    AppNavigation()
}