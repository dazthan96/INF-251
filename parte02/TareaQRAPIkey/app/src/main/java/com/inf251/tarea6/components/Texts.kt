package com.inf251.tarea6.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.inf251.tarea6.R

@Composable
fun TitleText(text: String,color: Int){
    Text(
        text = text,
        color= colorResource(color),
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
fun DescriptionText(content: String, color: Int){
    Text(
        text=content,
        color = colorResource(color),
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp

    )
}

@Preview(showSystemUi = true)
@Composable
fun ViewTextPreview(){
    Column(Modifier.fillMaxSize()){
        TitleText("Hola", R.color.colorView)
        TagText("Etiqueta")
        ContentText("welcome Android Studio")
        DescriptionText("Aprobado", R.color.black)
    }
}