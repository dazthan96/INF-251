package com.inf251.tarea6.screens

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inf251.tarea6.components.TitleText
import com.inf251.tarea6.R
import com.inf251.tarea6.components.FooterButtons
import com.inf251.tarea6.components.ReuseOutlineText
import com.inf251.tarea6.functions.LocacionActual
import com.inf251.tarea6.navigation.AppScreens

@Composable
fun FirstScreen(navController: NavController){
    var textToQr by remember { mutableStateOf("") }
    var lat by remember { mutableDoubleStateOf(0.0) }
    var lon by remember { mutableDoubleStateOf(0.0) }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if(isGranted){
            LocacionActual(context){location->
                location?.let{
                    lat = it.latitude
                    lon = it.longitude
                    textToQr = "Lat: ${it.latitude}, Lon: ${it.longitude}"
                }?:run{
                    Toast.makeText(context, "No se pudo obtener la ubicacion", Toast.LENGTH_SHORT ).show()
                }
            }
        }else{
            Toast.makeText(context, "Permiso Denegado", Toast.LENGTH_SHORT).show()
        }
    }
    Column (
        Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        TitleText("Agregar Texto", R.color.black)
        Column (
            Modifier.fillMaxSize().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            ReuseOutlineText(
                value=textToQr,
                onValueChange = {textToQr=it},
                label = "Texto a QR",
                enable = true,
                readOnly = false,
                type = KeyboardType.Text)
            Spacer(Modifier.height(8.dp))
            FooterButtons(
                text = "Locacion",
                idIcon = R.drawable.location,
                width = 0.75f,
                colorButton = R.color.colorDelete,
                colorContent = R.color.white
            ) {
                launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
            Spacer(Modifier.height(8.dp))
            FooterButtons(
                text = "Generar QR",
                idIcon = R.drawable.qr_code,
                width = 0.75f,
                colorButton = R.color.colorSuccess,
                colorContent = R.color.white
            ) {navController.navigate(AppScreens.SecondScreen.route+"/$textToQr") }
            Spacer(Modifier.height(8.dp))
            FooterButtons(
                text = "Abrir en Mapas",
                idIcon = R.drawable.book,
                width = 0.75f,
                colorButton = R.color.teal_700,
                colorContent = R.color.white
            ) {
                navController.navigate(AppScreens.ThirdScreen.route+"/${lat}/${lon}")
            }
        }

    }
}