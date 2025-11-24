package com.inf252.ubicacion

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import androidx.core.net.toUri
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient= LocationServices.getFusedLocationProviderClient(this)
        enableEdgeToEdge()
        setContent {
            Localizacion(fusedLocationClient)
        }
    }
}
@Composable
fun Localizacion(fusedLocationClient: FusedLocationProviderClient) {
    var locationText by remember { mutableStateOf("Ubicación no disponible") }

    val context = androidx.compose.ui.platform.LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (granted) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    locationText = "Lat: ${it.latitude}, Lon: ${it.longitude}"
                } ?: run {
                    locationText = "No se pudo obtener la ubicación"
                }
            }
        } else {
            locationText = "Permisos denegados"
        }
    }

    LaunchedEffect (Unit) {
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            launcher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        } else {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    locationText = "Lat: ${it.latitude}, Lon: ${it.longitude}"
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = locationText)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    locationText = "Lat: ${it.latitude}, Lon: ${it.longitude}"
                } ?: run {
                    locationText = "No se pudo obtener la ubicación"
                }
            }
        }) {
            Text("Actualizar ubicación")
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    val uri = "geo:${it.latitude},${it.longitude}?q=${it.latitude},${it.longitude}".toUri()
                    val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                        setPackage("com.google.android.apps.maps")
                    }
                    context.startActivity(intent)
                } ?: run {
                    locationText = "No se pudo obtener la ubicación"
                }
            }
        }) {
            Text("Abrir en Maps")
        }
    }
}

@Composable
fun OpenMapButton(latitude: Double, longitude: Double) {
    val context = LocalContext.current
    Button (onClick = {
        val uri = "geo:$latitude,$longitude?q=$latitude,$longitude".toUri()
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            setPackage("com.google.android.apps.maps")
        }
        context.startActivity(intent)
    }) {
        Text("Abrir en Google Maps")
    }
}
