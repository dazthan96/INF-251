package com.inf251.ejercicio03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BouncingImageScreen()
        }
    }
}

@Composable
fun BouncingImageScreen() {
    val density = LocalDensity.current
    val screenWidthPx = with(density) { LocalConfiguration.current.screenWidthDp.dp.toPx() }
    val screenHeightPx = with(density) { LocalConfiguration.current.screenHeightDp.dp.toPx() }
    val imageSizePx = with(density) { 100.dp.toPx() }


    val xOffset = remember { Animatable(0f) }
    val yOffset = remember { Animatable(0f) }

    var velocityX by remember { mutableFloatStateOf(5f) }
    var velocityY by remember { mutableFloatStateOf(5f) }
    var isRunning by remember { mutableStateOf(true) }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            xOffset.snapTo(xOffset.value + velocityX)
            yOffset.snapTo(yOffset.value + velocityY)

            if (xOffset.value < 0 || xOffset.value > screenWidthPx - imageSizePx) {
                velocityX *= -1
            }

            if (yOffset.value < 0 || yOffset.value > screenHeightPx - imageSizePx) {
                velocityY *= -1
            }

            delay(16)
        }
    }


    Box(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    isRunning = false
                }
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.umsa),
            contentDescription = null,
            modifier = Modifier
                .size(with(density){imageSizePx.toDp()})
                .offset {
                    with(density){
                        IntOffset(xOffset.value.toInt(), yOffset.value.toInt())
                    }

                }
        )
    }
}


