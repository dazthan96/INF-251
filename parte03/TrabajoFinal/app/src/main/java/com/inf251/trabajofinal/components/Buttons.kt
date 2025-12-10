package com.inf251.trabajofinal.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inf251.trabajofinal.R

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
            idIcon =R.drawable.home,
            colorButton = R.color.colorAdd,
            colorContent = R.color.white,
            //size =24 ,
            enabled = true,
        ) { }
        Buttons(
            text = "Guardar",
            colorButton = R.color.teal_200,
            colorContent = R.color.white,
            enabled = true) { }
        IconButtonColumn (
            text = "Añadir",
            idIcon = R.drawable.home,
            width = 150,
            colorButton = R.color.colorInfo,
            colorContent = R.color.black,
            enabled = true
        ) { }
        IconButtonRow (
            text = "Añadir",
            idIcon = R.drawable.home,
            width = 0.8f,
            colorButton = R.color.colorInfo,
            colorContent = R.color.black,
            enabled = true
        ) { }
    }

}




@Composable
fun IconButtons(idIcon:Int, colorButton:Int, colorContent:Int,size:Int=30, enabled: Boolean, onclick:()->Unit){
    IconButton(
        onClick = onclick,
        enabled = enabled,
        modifier=Modifier.size(size.dp),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = colorResource(colorButton),
            contentColor = colorResource(colorContent),
            disabledContentColor = colorResource(R.color.disabledcontent),
            disabledContainerColor = colorResource(R.color.disabledcontainer))
    ) {
        Icon(painter = painterResource(idIcon), contentDescription = "")
    }
}

@Composable
fun Buttons(text:String, colorButton: Int, colorContent: Int, enabled: Boolean, onclick:()->Unit){
    Button(
        onClick = onclick,
        shape= RoundedCornerShape(50),
        enabled = enabled,
        modifier=Modifier.width(130.dp),
        colors= ButtonDefaults.buttonColors(containerColor = colorResource(colorButton), contentColor = colorResource(colorContent))
    ) {
        Text(text)
    }
}
@Composable
fun IconButtonColumn(text:String, idIcon:Int, width:Int,colorButton: Int, colorContent: Int, enabled: Boolean,onclick:()->Unit){
    Button(
        onClick = onclick,
        shape= RectangleShape,
        enabled = enabled,
        modifier = Modifier.width(width.dp),
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
            Text(text)
        }
    }
}
@Composable
fun IconButtonRow(text:String, idIcon:Int, width:Float,colorButton: Int, colorContent: Int, enabled: Boolean,onclick:()->Unit){
    Button(
        onClick = onclick,
        shape= RectangleShape,
        enabled = enabled,
        modifier = Modifier.fillMaxWidth(width),
        colors= ButtonDefaults.buttonColors(
            containerColor = colorResource(colorButton),
            contentColor = colorResource(colorContent)
        )
    ) {
        Row (
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                painter = painterResource(idIcon),
                contentDescription = "",
                modifier=Modifier.size(20.dp)
            )
            Text(text)
        }
    }
}