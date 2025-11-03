package com.miapp.avance01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miapp.avance01.ui.theme.Avance01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Avance01Theme {

            }
            Interfaz()

        }
    }
}
@Preview
@Composable
fun Interfaz(){
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
        {

        Text("Bienvenido a Jetpack compose")
        Spacer(modifier = Modifier.height(height = 50.dp))
        Text("Avance en Android Studio")
        Spacer(modifier = Modifier.height(height = 50.dp))
        Image(painterResource(R.drawable.jetpack), contentDescription = "logo")
        Spacer(modifier = Modifier.height(height = 50.dp))
        Row (modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.Center) {
            Text("Introduzca su carnet de Identidad")
            Spacer(modifier = Modifier.width(width = 50.dp))
            var carnet by remember { mutableStateOf("")}
            TextField(value = carnet, onValueChange = {carnet=it})

        }
    }
}

