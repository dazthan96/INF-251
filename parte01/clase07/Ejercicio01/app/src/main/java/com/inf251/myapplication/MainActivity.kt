package com.inf251.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImageRotation()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImageRotation(){

    val rotation = remember { Animatable(0f) }
    var newAngle by remember { mutableStateOf<Float?>(null) }
    var result by remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(newAngle) {
        newAngle?.let{
            rotation.animateTo(
                targetValue = it,
                animationSpec = tween(2000, easing = FastOutSlowInEasing)
            )
            val finalAngle = rotation.value %360
            result = when (finalAngle) {
                in 0f..89f -> "YouTube"
                in 90f..179f -> "WhatsApp"
                in 180f..269f -> "TikTok"
                in 270f..359f -> "FaceBook"
                else -> ""
            }
            val intent = when (result) {
                "YouTube" -> Intent(Intent.ACTION_VIEW).apply {
                    data = "https://www.youtube.com/".toUri()}
                "WhatsApp" -> Intent(Intent.ACTION_VIEW).apply {
                    data = "https://www.whatsapp.com/?l=es".toUri()}
                "TikTok" -> Intent(Intent.ACTION_VIEW).apply {
                    data = "https://www.tiktok.com/explore".toUri()}
                "FaceBook" -> Intent(Intent.ACTION_VIEW).apply {
                    data = "https://www.facebook.com/".toUri()}
                else -> null
            }

            intent?.let {launchIntent ->
                context.startActivity(launchIntent)
            }
            newAngle=null
        }
    }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        TextField(
            value = result,
            onValueChange = {},
            label = {Text("Resultado")} ,
            readOnly = true
        )
        Spacer(Modifier.height(20.dp))
        Row (verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(R.drawable.ruleta),
                contentDescription = "ruleta",
                Modifier
                    .size(275.dp)
                    .graphicsLayer {
                        rotationZ = rotation.value
                    }
                    .clip(shape = CircleShape)
            )
            Icon(painterResource(
                R.drawable.arrow_left),
                contentDescription = "Indicador de ganador",
                Modifier
                    .size(75.dp)
            )
        }

        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            newAngle = rotation.value+(720..1200).random()

        }, Modifier.background(Color.Blue, shape = RoundedCornerShape(100))) {
            Text("Girar")
        }
    }
}