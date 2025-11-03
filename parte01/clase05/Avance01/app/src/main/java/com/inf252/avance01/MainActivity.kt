package com.inf252.avance01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
//import androidx.compose.animation.core.Animatable
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Animation1()
            Animation2()
        }
    }
}
@Composable
fun Animation1()
{
    var animated by remember { mutableStateOf(false) }
    val color=remember { Animatable(Color.Blue) }
    LaunchedEffect (animated) {
        color.animateTo(if (animated) Color.Green else  Color.Red,
            animationSpec = tween (6000))
    }
    Box(Modifier.fillMaxWidth().fillMaxHeight().background(color.value))
    Spacer(modifier = Modifier.padding(350.dp))

    Button(onClick = {animated=!animated},
        modifier = Modifier.padding(10.dp))
    {Text(text="painting")}
}
@Composable
fun Animation2() {
    val sequence = rememberInfiniteTransition()
    val size  by sequence.animateFloat(
        initialValue = 100.0f,
        targetValue = 250.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, delayMillis = 100, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Spacer(modifier = Modifier.padding(30.dp))
    Row {
        Spacer(modifier = Modifier.width(150.dp))
        Button(onClick = { System.exit(0)})
        { Text(text = "exit") }
        Spacer(modifier = Modifier.width(50.dp))
        Image(
            painter = painterResource(id = R.drawable.umsa),
            contentDescription = "best",
            modifier = Modifier.size(size.dp)
        )
    }
}