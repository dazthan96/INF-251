package com.clase03.ejercicio4

import android.R.drawable
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column (Modifier.padding(25.dp)){
                BoxText(
                    Modifier.fillMaxWidth(),
                    Color(10,50,130),
                    Alignment.CenterEnd,
                    15,
                    "Servicios Web",
                    20,
                    Color.White,
                    FontStyle.Normal

                )
                Box (Modifier.fillMaxWidth().fillMaxHeight(0.90f).padding(vertical = 10.dp), contentAlignment = Alignment.Center){
                    Row (Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                        Image(
                            painter = painterResource(R.drawable.imagen1),
                            contentDescription = "",
                            modifier = Modifier.fillMaxWidth(0.25f).padding(2.dp)
                        )
                        Box(Modifier.padding(2.dp).fillMaxWidth(0.34f), contentAlignment = Alignment.Center){
                            Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                                TextComposable(
                                    text = "Agendamiento Web \n",
                                    16,
                                    textColor = Color(10,50,130),
                                    textPad = 0,
                                    textStyle = FontStyle.Normal
                                )
                                Spacer(Modifier.height(20.dp))
                                Image(
                                    painter = painterResource(R.drawable.calendar),
                                    contentDescription = "",
                                    Modifier.fillMaxWidth(),
                                    contentScale = ContentScale.Crop,
                                    colorFilter = ColorFilter.tint(Color.Cyan))
                                Spacer(Modifier.height(20.dp))
                                BoxText(
                                    Modifier.fillMaxWidth(),
                                    Color(10,50,130),
                                    Alignment.Center,
                                    15,
                                    "Haz clic aquí",
                                    15,
                                    Color.White,
                                    FontStyle.Normal
                                )
                            }
                        }
                        Box(Modifier.padding(2.dp).fillMaxWidth(0.5f), contentAlignment = Alignment.Center){
                            Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                                TextComposable(
                                    text = "Certificado de NO Afiliación",
                                    16,
                                    textColor = Color(10,50,130),
                                    textPad = 0,
                                    textStyle = FontStyle.Normal
                                )
                                Spacer(Modifier.height(20.dp))
                                Image(
                                    painter = painterResource(R.drawable.icon_qr),
                                    contentDescription = "",
                                    Modifier.fillMaxWidth(),
                                    contentScale = ContentScale.Crop,
                                    colorFilter = ColorFilter.tint(Color.Cyan))
                                Spacer(Modifier.height(20.dp))
                                BoxText(
                                    Modifier.fillMaxWidth(),
                                    Color(10,50,130),
                                    Alignment.Center,
                                    15,
                                    "Emite tu certificado aquí",
                                    10,
                                    Color.White,
                                    FontStyle.Normal
                                )
                            }
                        }
                        Box(Modifier.padding(2.dp).fillMaxWidth(1f), contentAlignment = Alignment.Center){
                            Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                                TextComposable(
                                    text = "Certificado de NO Afiliado",
                                    16,
                                    textColor = Color(10,50,130),
                                    textPad = 0,
                                    textStyle = FontStyle.Normal
                                )
                                Spacer(Modifier.height(20.dp))
                                Image(
                                    painter = painterResource(R.drawable.download),
                                    contentDescription = "",
                                    Modifier.fillMaxWidth(),
                                    contentScale = ContentScale.Crop,
                                    colorFilter = ColorFilter.tint(Color.Cyan))
                                Spacer(Modifier.height(20.dp))
                                BoxText(
                                    Modifier.fillMaxWidth(),
                                    Color(10,50,130),
                                    Alignment.Center,
                                    15,
                                    "Descarga aquí",
                                    16,
                                    Color.White,
                                    FontStyle.Normal
                                )
                            }
                        }
                    }
                }
                BoxText(
                    Modifier.fillMaxWidth(),
                    Color(10,50,130),
                    Alignment.Center,
                    15,
                    "Notas de Prensa",
                    15,
                    Color.White,
                    FontStyle.Italic
                )
            }
        }
    }
}

@Preview
@Composable
fun PrevText(){
    TextComposable("hola mundo", 24, Color(10,50,130),15, FontStyle.Italic)
}

@Composable
fun TextComposable(text: String, textSize: Int, textColor: Color, textPad: Int, textStyle: FontStyle ){
    Text(text, color = textColor, fontSize = textSize.sp, fontStyle = textStyle, modifier=Modifier.padding(textPad.dp),
        textAlign = TextAlign.Center)
}
@Composable
fun BoxText(
    boxSize:Modifier,
    boxColor: Color,
    position: Alignment,
    textPad: Int,
    text:String,
    textSize: Int,
    textColor: Color,
    textStyle: FontStyle){
    Box(modifier = boxSize.background(color=boxColor), contentAlignment = position){
        TextComposable(text=text, textSize, textColor, textPad, textStyle)
    }
}
@Preview
@Composable
fun PrevBoxText(){
    BoxText(
        Modifier.fillMaxWidth(),
        Color(10,50,130),
        Alignment.Center,
        15,
        "Servicios Web",
        20,
        Color.White,
        FontStyle.Italic

    )
}