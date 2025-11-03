package com.inf251.ejercicio02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ventilador()
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun Ventilador(){
    var velocidad by remember { mutableStateOf("off") }
    val rotacion = remember { Animatable(   0f) }
    LaunchedEffect(velocidad) {
        rotacion.stop()
        when (velocidad){
            "fast"->{
                while (true){
                    rotacion.animateTo(
                        targetValue = rotacion.value + 360f,
                        animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                    )
                }
            }
            "medium"->{
                while (true){
                    rotacion.animateTo(
                        targetValue = rotacion.value + 360f,
                        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
                    )
                }
            }
            "slow"->{
                while (true){
                    rotacion.animateTo(
                        targetValue = rotacion.value + 360f,
                        animationSpec = tween(durationMillis = 2000, easing = LinearEasing)
                    )
                }
            }
            else -> {}
        }
    }

    Column (Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        TextField(
            value = velocidad,
            onValueChange = {},
            label = {Text("Velocidad Actual")},
            readOnly = true
        )
        Spacer(Modifier.height(20.dp))
        Row (Modifier.fillMaxWidth(0.9f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Image(
                painter=painterResource(R.drawable.ventilador),
                contentDescription = "ventilador",
                Modifier
                    .size(100.dp)
                    .graphicsLayer{
                        rotationZ = rotacion.value % 360f
                    }
            )
            Spacer(Modifier.width(50.dp))
            Column {
                ButtonVel("MAXIMO") {velocidad = "fast" }
                ButtonVel("MINIMO") {velocidad = "slow" }
                ButtonVel("REGULAR") {velocidad = "medium" }
                ButtonVel("APAGADO") {velocidad = "off" }

            }
        }
    }
}
@Composable
fun ButtonVel(valor: String, onClick:()-> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier.width(130.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(39, 156, 245)
        )
    ) {Text(valor) }
}