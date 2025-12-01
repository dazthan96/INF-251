package com.inf251.tarea6.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.inf251.tarea6.components.TitleText
import com.inf251.tarea6.R
import com.inf251.tarea6.components.ContentText
import com.inf251.tarea6.components.FooterButtons
import com.inf251.tarea6.components.TagText
import com.inf251.tarea6.navigation.AppScreens

@Composable
fun ThirdScreen(navController: NavController, lat: Double, lon: Double){
    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(LatLng(lat,lon),15f)
    }
    Column (
        Modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        TitleText("Tercera Pantalla", R.color.black)
        Column (
            Modifier.fillMaxSize().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Card (Modifier.fillMaxWidth(0.8f).padding(10.dp)){
                Column (
                    Modifier.fillMaxWidth().padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Row {
                        TagText("Latitud: ")
                        ContentText("$lat")
                    }
                    Row {
                        TagText("Longitud: ")
                        ContentText("$lon")
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            GoogleMap (
                modifier = Modifier.fillMaxSize(0.7f),
                cameraPositionState = cameraPositionState

            ){
                Marker (
                    state = MarkerState(position = LatLng(lat, lon)),

                    title = "Ubicacion Actual"
                )
            }
            Spacer(Modifier.height(8.dp))

            FooterButtons(
                text = "Primer Pantalla",
                idIcon = R.drawable.home,
                width = 0.75f,
                colorButton = R.color.colorView,
                colorContent = R.color.white
            ) {navController.navigate(AppScreens.FirstScreen.route) }
        }
    }
}