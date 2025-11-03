package com.inf252.tarea01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RotaFlotante()
        }
    }
}
@Composable
fun ImageModifier (imageSize:Dp)
{
    Image(painter = painterResource(id = R.drawable.umsa),
        contentDescription = "la mejor",
        modifier= Modifier
            .size(imageSize)
            .clip(CircleShape)
            .border(5.dp, Color.LightGray, CircleShape))

}
@Composable
fun GetDp()
{
    val enlarge= rememberSaveable { mutableStateOf(false) }
    val sizeDp: Dp by animateDpAsState(targetValue = if (enlarge.value) 350.dp else 100.dp)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ImageModifier(imageSize = sizeDp)
        Button(
            onClick = { enlarge.value = !enlarge.value },
            modifier = Modifier
                .padding(50.dp)
                .width(300.dp)
        )
        { Text(text = "modifica estado en Dp") }
    }
}

@Composable
fun RotaFlotante()
{
    var rotado by rememberSaveable { mutableStateOf(false) }
    val angulo by animateFloatAsState(targetValue = if (rotado) 360F else 0F,
        animationSpec = tween (2500))
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.umsa),
            contentDescription = "la mejor",
            modifier = Modifier
                .rotate(angulo)
                .size(150.dp)
        )
        Button(onClick =  {rotado =!rotado },
            modifier = Modifier
                .padding(50.dp)
                .width(200.dp))
        {Text(text="empieza a rotar") }
        GetDp()
    }

}