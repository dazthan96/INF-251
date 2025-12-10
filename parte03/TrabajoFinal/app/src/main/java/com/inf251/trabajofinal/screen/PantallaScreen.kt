package com.inf251.trabajofinal.screen

import android.Manifest
import android.app.Activity
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inf251.trabajofinal.R
import com.inf251.trabajofinal.components.IconButtonColumn
import com.inf251.trabajofinal.components.TitleText
import com.inf251.trabajofinal.navigation.AppScreen
import kotlinx.coroutines.delay
import java.lang.Exception

@Composable
fun PantallaScreen(navController: NavController){
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val window = (context as Activity).window
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        onDispose {
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }
    var seconds by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        seconds = 0
        while (true){
            delay(1000)
            seconds++
        }
    }
    Column (Modifier
        .fillMaxSize()
        .background(colorResource(R.color.cardColor))){
        Column(Modifier.fillMaxSize().padding(35.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            TitleText(
                text = "Pantalla",
                color = R.color.black
            )
            Spacer(Modifier.height(15.dp))
            Text("Esta pantalla nunca se apagara")
            Spacer(Modifier.height(15.dp))
            Text("Tiempo : $seconds segundos")
            Spacer(Modifier.height(15.dp))
            Icon(
                painter = painterResource(R.drawable.screen),
                contentDescription = "",
                modifier = Modifier.size(250.dp),
                tint = if (seconds%2==0){
                    colorResource(R.color.colorInfo)
                }else{
                    colorResource(R.color.disabledcontent)
                }
            )
            Spacer(Modifier.height(15.dp))
            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly){

                IconButtonColumn(
                    text = "Reiniciar",
                    idIcon = R.drawable.screen,
                    width = 150,
                    colorButton = R.color.colorEdit,
                    colorContent = R.color.white,
                    enabled = true
                ) {
                    seconds=0
                }
                IconButtonColumn(
                    text = "Salir",
                    idIcon = R.drawable.home,
                    width = 150,
                    colorButton = R.color.colorView,
                    colorContent = R.color.white,
                    enabled = true
                ){
                    navController.navigate(AppScreen.FirstScreen.route)
                }
            }
        }

    }
}