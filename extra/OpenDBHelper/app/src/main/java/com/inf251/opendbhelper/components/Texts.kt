package com.inf251.opendbhelper.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inf251.opendbhelper.R

@Composable
fun TitleText(text: String,color: Int){
    Text(
        text = text,
        color= colorResource(color),
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
        letterSpacing = 2.sp,
        fontSize = 25.sp,
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        textAlign = TextAlign.Center)
}

fun ToastReuse(context: Context, text:String){
    Toast.makeText(context, text,Toast.LENGTH_SHORT).show()
}
@Composable
fun ContentText(contenido: String, color: Int){
    Text(
        text=contenido,
        fontSize = 15.sp,
        color=colorResource(color)
    )
}

@Composable
fun TagText(contenido: String, color: Int){
    Text(
        text=contenido,
        fontSize = 15.sp,
        color = colorResource(color),
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp,

        )
}

@Composable
fun DescriptionText(contenido: String, color: Int){
    Text(
        text=contenido,
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
        Box (
            Modifier.fillMaxWidth().background(colorResource(R.color.primaryColor))){
            TitleText(
                text = "Hola",
                color = R.color.titleColor)
        }
        Column (
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally){
            Card (Modifier.fillMaxWidth(0.9f).padding(8.dp),
                colors = CardDefaults.cardColors(colorResource(R.color.cardColor))
            ){
                Column (Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)){
                    TagText(
                        contenido = "Etiqueta",
                        color = R.color.titleColor)
                    ContentText(
                        contenido = "welcome Android Studio",
                        color = R.color.textColor)
                    DescriptionText(
                        contenido = "Aprobado",
                        color = R.color.colorAdd)
                }
            }
        }

    }
}