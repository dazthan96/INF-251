package com.clase03.ejercicio1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Imagenes(
                    R.drawable.google,
                    modifier = Modifier.width(250.dp),
                    "Logo Google"
                )
                Spacer(modifier = Modifier.height(height = 35.dp))
                var pregunta by remember { mutableStateOf("") }
                OutlinedTextField(

                    value = pregunta,
                    onValueChange = {pregunta =it},
                    singleLine = true,

                    shape = RoundedCornerShape(50),
                    modifier = Modifier.fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 20.dp),

                    placeholder ={
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Text(
                                "Buscar en Google o escribir una URL",
                                //fontSize = 16.sp
                            )
                        } },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            tint = Color.Gray
                        )
                    },
                    trailingIcon = {
                        Box(modifier = Modifier.padding(10.dp)){
                            Row {
                                Image(painter=painterResource(R.drawable.mic), contentDescription = "")
                                Spacer(modifier=Modifier.width(2.dp))
                                Image(painterResource(id = R.drawable.camera), modifier = Modifier.size(24.dp), contentDescription = "")

                            }
                        }

                    }

                )
                Spacer(modifier = Modifier.height(height = 35.dp))
                Row (verticalAlignment = Alignment.CenterVertically){
                    CardIcons("Facebook",R.drawable.facebooklogo,Modifier.size(25.dp), "Logo de Facebook")
                    Spacer(modifier = Modifier.width(5.dp))
                    CardIcons("Youtube",R.drawable.youtubelogo,Modifier.size(25.dp), "Logo de Youtube")
                    Spacer(modifier = Modifier.width(5.dp))
                    CardIcons("Google",R.drawable.googlelogo,Modifier.size(25.dp), "Logo de Google")
                    Spacer(modifier = Modifier.width(5.dp))
                    CardIcons("WhatsApp",R.drawable.whatsapplogo,Modifier.size(25.dp), "Logo de WhatsApp")
                    Spacer(modifier = Modifier.width(5.dp))
                    CardIcons("Agregar",R.drawable.ic_add,Modifier.size(25.dp), "Logo de AD")
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
        }
    }
}

@Composable
fun Imagenes(imageId: Int, modifier: Modifier = Modifier, description: String){
    val imagen: Painter = painterResource(id=imageId)
    Image(
        painter = imagen,
        contentDescription = description,
        modifier = modifier
    )
}

@Composable
fun CardIcons(nombre: String, imageId: Int, modifier:Modifier = Modifier, description: String){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)) {
        Box (modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(color = Color.LightGray), contentAlignment = Alignment.Center
        ) {
            Imagenes(imageId, modifier, description)
        }
        Spacer(modifier.height(height = 5.dp))
        Text(text=nombre, fontSize = 10.sp)
    }
}
@Preview
@Composable
fun PrevImage(){
    CardIcons("Facebook",R.drawable.facebooklogo,Modifier.size(40.dp), "Logo de Facebook")
}