package com.inf251.gestionnotas.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(text: String,color: Color){
    Text(
        text = text,
        color=color,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
        textDecoration = TextDecoration.Underline,
        letterSpacing = 3.sp,
        fontSize = 25.sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,

    )
}

@Composable
fun ContentText(content: String){
    Text(
        text=content,
        fontSize = 15.sp
    )
}

@Composable
fun TagText(content: String){
    Text(
        text=content,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
    )
}

@Composable
fun DescriptionText(content: String, color: Color){
    Text(
        text=content,
        color = color,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp

    )
}

@Preview(showSystemUi = true)
@Composable
fun ViewTextPreview(){
    Column (Modifier.fillMaxSize()){
        TitleText("Hola",Color.Red)
        TagText("Etiqueta")
        ContentText("welcome Android Studio")
        DescriptionText("Aprobado", Color.Blue)
    }
}