package com.inf252.cabrera_quispe_luis_alberto_parcial1.screens

//import com.inf252.cabrera_quispe_luis_alberto_parcial1.R
//para importar iamgenes debemos primerpotar primero la carpeta de recursos con la linea de arriba debido a la estructura de la aplicacion
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.inf252.cabrera_quispe_luis_alberto_parcial1.navigation.AppNavigation
import com.inf252.cabrera_quispe_luis_alberto_parcial1.funtions.repetirVocales

@Composable
fun ThirdScreen(navController: NavController,text: String?){
    var fontSize by remember { mutableIntStateOf(20) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    val texto = repetirVocales("$text")

    Column (
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Box(

        ) {
            Text(
                text = texto,
                fontSize = fontSize.sp,
                modifier = Modifier.offset(x = offsetX.dp)
            )
        }

        Row (
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = {
                fontSize += 4
                offsetX += 20f
            }, Modifier.width(125.dp)) {
                Text("+", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            Button(onClick = {
                fontSize = maxOf(12, fontSize - (4))
                offsetX = maxOf(-250f, offsetX - 20f)
            }, Modifier.width(125.dp)) {
                Text("-", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun Prev3App(){
    AppNavigation()
}

