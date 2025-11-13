package com.inf251.gestionnotas.components

import androidx.compose.foundation.layout.Arrangement
import com.inf251.gestionnotas.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReuseButtons(text :String, colorButton: Color, colorText:Color,  onClick:()-> Unit){
    Button(

        onClick = onClick,
        shape = RoundedCornerShape(50),
        modifier = Modifier.width(120.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorButton)
    ) {
        Text(
            text = text,
            color = colorText
        )
    }
}

@Composable
fun ReuseIconButtons(idIcon: Int, colorButton: Color, colorIcon: Color,onclick:()->Unit){
    IconButton(
        onClick = onclick,
        colors = IconButtonDefaults.iconButtonColors(colorButton, colorIcon) ,

    ) {
        Icon(
            painter = painterResource(idIcon),
            contentDescription = ""

        )
    }
}
@Composable
fun ReuseBarButton(idIcon: Int, text: String,width: Float,colorButton: Color,colorText: Color, enable:Boolean,onClick: () -> Unit){
    Button(
        onClick = onClick,
        shape = RectangleShape,
        modifier = Modifier.fillMaxWidth(width),
        enabled = enable,
        colors = ButtonDefaults.buttonColors(colorButton,colorText)) {
        Column (
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Icon(
                painter = painterResource(idIcon),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
            Text(text, fontSize = 10.sp)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ViewButtonsPreview(){
    Column (Modifier.fillMaxSize()){
        ReuseButtons("guardar", Color.Blue,Color.White, { })
        ReuseButtons("retroceder", Color.Blue,Color.White, { })
        ReuseIconButtons(R.drawable.add,Color.Red,Color.White,{})
        Row(Modifier.fillMaxWidth()){
            ReuseBarButton(R.drawable.docente,"Docente",0.3f,AddColor,Color.White,false,{})
            ReuseBarButton(R.drawable.estudiante,"Estudiante",0.5f,AddColor,Color.White,true,{})
            ReuseBarButton(R.drawable.materia,"Materia",1.0f,AddColor,Color.White,true,{})
        }
    }
}