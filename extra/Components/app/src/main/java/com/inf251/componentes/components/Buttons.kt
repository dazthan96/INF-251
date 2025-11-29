package com.inf251.componentes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inf251.componentes.R

@Preview(showSystemUi = true)
@Composable
fun ButtonsPreview(){
    Column (
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        IconButtons(
            idIcon =R.drawable.add,
            colorButton = R.color.colorAdd,
            colorContent = R.color.white) { }
        Buttons(
            "Guardar",
            R.color.teal_200,
            R.color.white) { }
        FooterButtons(
            "Añadir",
            R.drawable.add,
            0.8f,
            R.color.colorInfo,
            R.color.black
        ) { }
    }

}




@Composable
fun IconButtons(idIcon:Int, colorButton:Int, colorContent:Int, onclick:()->Unit){
    IconButton(
        onClick = onclick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = colorResource(colorButton),
            contentColor = colorResource(colorContent))
    ) {
        Icon(painter = painterResource(idIcon), contentDescription = "")
    }
}

@Composable
fun Buttons(text:String, colorButton: Int, colorContent: Int, onclick:()->Unit){
    Button(
        onClick = onclick,
        shape= RoundedCornerShape(50),
        modifier=Modifier.width(120.dp),
        colors= ButtonDefaults.buttonColors(containerColor = colorResource(colorButton), contentColor = colorResource(colorContent))
    ) {
        Text(text)
    }
}
@Composable
fun FooterButtons(text:String, idIcon:Int, width:Float,colorButton: Int, colorContent: Int, onclick:()->Unit){
    Button(
        onClick = onclick,
        shape= RectangleShape,
        modifier = Modifier.fillMaxWidth(width),
        colors= ButtonDefaults.buttonColors(
            containerColor = colorResource(colorButton),
            contentColor = colorResource(colorContent)
        )
    ) {
        Column (
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Icon(
                painter = painterResource(idIcon),
                contentDescription = "",
                modifier=Modifier.size(20.dp)
            )
            Text(text, fontFamily = FontFamily())
        }
    }
}

