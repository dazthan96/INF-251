package com.inf251.trabajofinal.screen

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.inf251.trabajofinal.components.TitleText
import com.inf251.trabajofinal.R
import com.inf251.trabajofinal.components.TagText
import com.inf251.trabajofinal.navigation.AppScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun FirstScreen(navController: NavController){
    var opcion by remember { mutableStateOf("") }

    Column (Modifier
        .fillMaxSize()
        .background(colorResource(R.color.cardColor))){
        Row (Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
        ){
            TitleText(
                text = "MENU DE ACTIVACION DE DISPOSITIVOS",
                color = R.color.black
            )
        }
        Column(Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
            Column (Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .border(
                    width = 3.dp,
                    color = colorResource(R.color.black),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 30.dp, horizontal = 20.dp)){
                TagText(
                    contenido = "1.- ACTIVAR WIFI",
                    color = R.color.black
                )
                TagText(
                    contenido = "2.- ACTIVAR BLUETOOTH",
                    color = R.color.black
                )
                TagText(
                    contenido = "3.- ACTIVAR MICROFONO",
                    color = R.color.black
                )
                TagText(
                    contenido = "4.- PANTALLA",
                    color = R.color.black
                )
                TagText(
                    contenido = "5.- ALTAVOZ",
                    color = R.color.black
                )
                TagText(
                    contenido = "6.- CAMARA",
                    color = R.color.black
                )
                TagText(
                    contenido = "7.- SALIR",
                    color = R.color.black
                )
                Spacer(Modifier.height(25.dp))
                Row (
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    TagText("DIGITE OPCION",R.color.black)
                    Icon(
                        painter = painterResource(R.drawable.arrow_forward),
                        contentDescription = "",
                        tint = colorResource(R.color.black)
                    )
                    BasicTextField(
                        value = opcion,
                        onValueChange = {
                                nuevo ->
                            if (nuevo.length <= 1 && nuevo.all { it.isDigit() }) {
                                opcion = nuevo
                            }
                        },
                        modifier = Modifier
                            .size(40.dp)
                            .border(1.dp, colorResource(R.color.black), RoundedCornerShape(4.dp))
                            .padding(0.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 25.sp, textAlign = TextAlign.Center)
                    )
                    Spacer(Modifier.height(15.dp))
                    when (opcion){
                        "1"->navController.navigate(AppScreen.WifiScreen.route)
                        "2"->navController.navigate(AppScreen.BTScreen.route)
                        "3"->navController.navigate(AppScreen.MicScreen.route)
                        "4"->navController.navigate(AppScreen.PantallaScreen.route)
                        "5"->navController.navigate(AppScreen.SpeakerScreen.route)
                        "6"->navController.navigate(AppScreen.CameraScreen.route)
                        "7"->{
                            val activity = (navController.context as? Activity)
                            activity?.finishAffinity()
                        }
                        else->Text("")
                    }
                }
            }
        }
    }
}
