package com.inf252.myapplication

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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
            RotateFloat()
        }
    }
}
@Composable
fun ImageModify (imageSize: Dp)
{
    Image(painter = painterResource(id = R.drawable.umsa),
        contentDescription = "la mejor",
        modifier= Modifier
            .size(imageSize)
            .clip(CircleShape)
            .border(5.dp,Color.LightGray,CircleShape))
}
@Composable
fun ObtieneDp()
{
    val toEnlarge= rememberSaveable { mutableStateOf(false) }
    val sizeDp: Dp by animateDpAsState(targetValue = if (toEnlarge.value) 350.dp else 100.dp)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ImageModify(imageSize = sizeDp)
        Button(
            onClick = { toEnlarge.value = !toEnlarge.value },
            modifier = Modifier
                .padding(50.dp)
                .width(300.dp)
        )
        { Text(text = "modifica estado en Dp") }
    }
}
@Composable
fun RotateFloat()
{

    var rotado by rememberSaveable { mutableStateOf(false) }
    val angulo by animateFloatAsState(targetValue = if (rotado) 360F else 0F,
        animationSpec = tween(2500))
    var degreesAng by remember { mutableIntStateOf(0) }
    var angle by remember { mutableStateOf("") }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row (verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = R.drawable.ruleta),
                contentDescription = "la mejor",
                modifier = Modifier
                    .rotate(angulo + degreesAng)
                    .size(150.dp)
            )
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")

        }

        Button(onClick = {degreesAng = (0..1000).random()},
            modifier = Modifier
                .padding(50.dp)
                .width(200.dp),
            )
        {Text(text="empieza a rotar") }
        ObtieneDp()
    }
}