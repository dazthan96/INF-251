package com.inf251.tarea6.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inf251.tarea6.components.TitleText
import com.inf251.tarea6.R
import com.inf251.tarea6.components.FooterButtons
import com.inf251.tarea6.components.ReuseOutlineText
import com.inf251.tarea6.components.TagText
import com.inf251.tarea6.functions.GenerarQr
import com.inf251.tarea6.navigation.AppScreens

@Composable
fun SecondScreen(navController: NavController, textToQr:String){
    var contentText by remember { mutableStateOf(textToQr) }
    val qrBitmap = remember(contentText) { GenerarQr(contentText) }
    Column (Modifier
        .fillMaxSize()
        .padding(8.dp)){
        TitleText("Segunda Pantalla", R.color.black )
        Column (
            Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            ReuseOutlineText(
                value = contentText,
                onValueChange = {newText ->contentText=newText},
                label = "Texto a QR",
                enable = true,
                readOnly = false,
                type = KeyboardType.Text
            )
            Spacer(Modifier.height(8.dp))
            TagText("El texto convertido en QR")
            Spacer(Modifier.height(8.dp))
            qrBitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier.size(250.dp)
                )
            }?: run{
                Image(
                    painter = painterResource(R.drawable.qr_code),
                    contentDescription = "",
                    modifier = Modifier.size(250.dp))
            }
            Spacer(Modifier.height(8.dp))
            FooterButtons(
                text="Primera Pantalla",
                idIcon = R.drawable.home,
                width = 0.75f,
                colorButton = R.color.colorView,
                colorContent = R.color.white
            ) { navController.navigate(AppScreens.FirstScreen.route)}

        }
    }
}