package com.inf252.avance01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable // Para animar valores de formacontrolada
import androidx.compose.animation.core.tween // Para definir la duración y tipo de  animación
import androidx.compose.foundation.background // Para aplicar color de fondo al objeto
import androidx.compose.foundation.border // Para dibujar el borde del contenedor
import androidx.compose.foundation.layout.Box // Para crear el contenedor principal
import androidx.compose.foundation.layout.offset // Para mover el objeto dentro del contenedor
import androidx.compose.foundation.layout.size // Para definir tamaños de los elementos
import androidx.compose.runtime.Composable // Para declarar funciones composables
import androidx.compose.runtime.LaunchedEffect // Para lanzar efectos secundarios (animaciones)
import androidx.compose.runtime.remember // Para mantener estado entre recomposiciones
import androidx.compose.ui.Modifier // Para aplicar modificadores a los elementos
import androidx.compose.ui.graphics.Color // Para definir colores
import androidx.compose.ui.unit.dp // Para trabajar con unidades de medida
import androidx.compose.ui.unit.IntOffset // Para representar desplazamientos en píxeles
import androidx.compose.foundation.shape.CircleShape // Para dar forma circular al objeto
import androidx.compose.ui.platform.LocalDensity // (Opcional) Para convertir dp a px si lo haces manualmente
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Muevebalonenborde()
        }
    }
}
@Composable
fun Muevebalonenborde() {
    // Tamaño del contenedor donde se moverá el objeto
    val boxSize = 400.dp
    // Tamaño del objeto (círculo rojo)
    val circleSize = 30.dp
    // Duración de la animación por cada lado (en milisegundos)
    val duration = 1000
    // Variables animables para controlar la posición X e Y
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    // Acceso a la densidad actual para convertir Dp a Px
    val density = LocalDensity.current
    // Efecto que lanza la animación al iniciar el Composable
    LaunchedEffect(Unit) {
        // Convertimos Dp a Px dentro del contexto de densidad
        val boxPx = with(density) { boxSize.toPx() }
        val circlePx = with(density) { circleSize.toPx() }
        while (true) {
            // Mover hacia la derecha
            offsetX.animateTo(boxPx - circlePx, animationSpec = tween(durationMillis = duration))
            // Mover hacia abajo
            offsetY.animateTo(boxPx - circlePx, animationSpec = tween(durationMillis = duration))
            // Mover hacia la izquierda
            offsetX.animateTo(0f, animationSpec = tween(durationMillis = duration))
            // Mover hacia arriba
            offsetY.animateTo(0f, animationSpec = tween(durationMillis = duration))
        }
    }
    // Contenedor principal con borde visible
    Box(
        modifier = Modifier
            .size(boxSize)
            .border(5.dp, Color.Gray)
    ) {
        // Objeto animado (círculo rojo) que se mueve por el borde
        Box(
            modifier = Modifier
                .size(circleSize)
                .offset {
                    // Posición actual del objeto según las animaciones
                    IntOffset(offsetX.value.toInt(), offsetY.value.toInt())
                }
                .background(Color.Red, shape = CircleShape)
        )
    }
}