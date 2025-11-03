package com.clase03.ejercicio3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(Modifier.fillMaxWidth().padding(20.dp)) {
                Row (Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween){
                    Text(text = "WhatsApp", color = Color(0xFF25D366), fontWeight = FontWeight.Bold, fontSize = 25.sp)
                    Box {
                        Row {
                            Image(
                                painterResource(R.drawable.add_message),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(25.dp), colorFilter = ColorFilter.tint(Color.Gray)
                            )
                            Spacer(modifier= Modifier.width(8.dp))
                            Image(
                                painterResource(R.drawable.three_dots),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(25.dp), colorFilter = ColorFilter.tint(Color.Gray)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(height = 30.dp))
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
                                "Ingrese su busqueda",
                                //fontSize = 16.sp
                            )
                        } },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            tint = Color.Gray
                        )
                    }

                )
                Spacer(modifier = Modifier.height(height = 30.dp))
                Row (Modifier.padding(horizontal = 4.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                    Buttons("Todos",backgroundColor = Color(0xFFDCF8C6),Color(10,120,45), borderColor = Color.LightGray )
                    Buttons("No leídos",backgroundColor = Color.White,Color.Black, borderColor = Color.LightGray )
                    Buttons("Favoritos",backgroundColor = Color.White,Color.Black, borderColor = Color.LightGray )
                    Buttons("Grupos",backgroundColor = Color.White,Color.Black, borderColor = Color.LightGray )
                }
                Spacer(Modifier.height(35.dp))
                Text("Chats", fontSize = 15.sp)
                Spacer(Modifier.height(35.dp))
                BoxChats(R.drawable.ic_dos, pregunta, "Umsawilmaprefas@", "gracia","12:30", Color.White)
                BoxChats(R.drawable.ic_uno, pregunta, "Docente Informatica Umsa", "- Moni: Muy buenas tardes Señores Dec...","ayer", Color.White)
            }
        }
    }
}
@Composable
fun Buttons(text: String, backgroundColor: Color, textColor: Color, borderColor: Color){
    Box (
        modifier = Modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(50.dp))
            .border(width = 2.dp, color = borderColor, shape = RoundedCornerShape(30.dp))
            .padding(horizontal = 16.dp, vertical = 6.dp)

    ){
        Text(text, color = textColor, fontSize = 15.sp)
    }
}

@Composable
fun TextConPalabra(text: String, palabraClave: String) {
    val annotatedString = buildAnnotatedString {
        val regex = Regex(palabraClave, RegexOption.IGNORE_CASE)
        var lastIndex = 0

        regex.findAll(text).forEach { resultado ->
            val inicio = resultado.range.first
            val fin = resultado.range.last + 1

            append(text.substring(lastIndex, inicio))

            withStyle(style = SpanStyle(color = Color(0xFF25D366), fontWeight = FontWeight.Bold)) {
                append(text.substring(inicio, fin))
            }
            lastIndex = fin
        }
        if (lastIndex < text.length) {
            append(text.substring(lastIndex))
        }
    }

    Text(text = annotatedString)
}

@Composable
fun BoxChats(idImage:Int,keyWord:String, nameUser: String, msUser:String, dateMs:String,  backgroundColor: Color){
    val image: Painter = painterResource(idImage)
    Box(modifier = Modifier
        .background(color = backgroundColor)
        .padding(4.dp)
    ){
        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Image(painter = image, contentDescription = "", Modifier
                .clip(shape = CircleShape)
                .size(50.dp)
                .padding(2.dp), contentScale = ContentScale.Crop)
            Column (modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)){
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    TextConPalabra(nameUser,keyWord)
                    Text(dateMs, color = Color.LightGray)
                }
                Text(msUser, color = Color.LightGray)
            }
        }

    }
}

@Composable
fun PreButton(){
    Buttons("Todos",backgroundColor = Color(0xFFDCF8C6),Color(10,120,45), borderColor = Color.LightGray )
}

@Preview
@Composable
fun PrevChatBox(){
    BoxChats(R.drawable.ic_launcher_background, "Umsa", "Umsawilmaprefas@", "gracia","12:30", Color.White)
}

