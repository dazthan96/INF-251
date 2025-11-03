package com.inf252.cabrera_quispe_luis_alberto_parcial1.screens

import com.inf252.cabrera_quispe_luis_alberto_parcial1.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inf252.cabrera_quispe_luis_alberto_parcial1.navigation.AppNavigation
import com.inf252.cabrera_quispe_luis_alberto_parcial1.navigation.AppScreens

@Composable
fun FirstScreen(navController: NavController){
    var text by remember { mutableStateOf("") }
    Column (Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text("GENERADOR DE NOMBRES")
        Spacer(Modifier.height(10.dp))
        Image(
            painter = painterResource(R.drawable.letras),
            contentDescription = "",
            Modifier.size(150.dp)
        )
        Spacer(Modifier.height(10.dp))
        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("INTRODUCIR NOMBRE")
            Spacer(Modifier.width(8.dp))
            TextField(
                value = text,
                onValueChange = {newText ->
                    if (newText.length<=5){
                        text = newText
                    }
                },
                modifier = Modifier.fillMaxWidth(.75f)
            )
        }
        Spacer(Modifier.height(8.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    navController.navigate(AppScreens.SecondScreen.route + "/$text")
                },
                Modifier.width(135.dp).clip(shape = RectangleShape)
            ) {
                Text("PRIMER ANIMACION", textAlign = TextAlign.Center)
            }
            Button(
                onClick = {
                    navController.navigate(AppScreens.ThirdScreen.route + "/$text")
                },
                Modifier.width(135.dp)
            ) {
                Text("SEGUNDA ANIMACION", textAlign = TextAlign.Center)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Prev1App(){
    AppNavigation()
}
