package com.inf251.examen2.screens

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.inf251.examen2.R
import com.inf251.examen2.components.Buttons
import com.inf251.examen2.components.IconButtonRow
import com.inf251.examen2.components.ToastReuse
import com.inf251.examen2.functions.CrearImageUri
import com.inf251.examen2.navigation.AppScreens

@Composable
fun CameraScreen(navController: NavController){
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember{mutableStateOf<android.graphics.Bitmap?>(null)}

    //Lanzador de la camara
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success->
        if(success &&imageUri!=null){
            context.contentResolver.openInputStream(imageUri!!)?.use {
                bitmap = BitmapFactory.decodeStream(it)
            }
            ToastReuse(context, "Foto Guardada")
        }else{
            ToastReuse(context, "Nose tomo la foto")
        }
    }
    // LAnzador de permisos
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted->
        if(granted){
            val uri = CrearImageUri(context)
            imageUri = uri
            uri?.let { cameraLauncher.launch(it) }
        }else{
            ToastReuse(context, "Permiso Denegado")
        }

    }

    Column (
        Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
            Buttons(
                "Pagina 1",
                R.color.teal_200,
                R.color.white,
                enabled = true
            ) { navController.navigate(AppScreens.FirstScreen.route)}
            Spacer(Modifier.width(5.dp))
            Buttons(
                "Tomar foto",
                R.color.teal_700,
                R.color.white,
                enabled = true,
            ) {
                val permission = Manifest.permission.CAMERA
                if(ContextCompat.checkSelfPermission(context, permission)== PackageManager.PERMISSION_GRANTED){
                    val uri = CrearImageUri(context)
                    imageUri = uri
                    uri?.let { cameraLauncher.launch(it) }
                }else{
                    permissionLauncher.launch(permission)
                }
            }
        }
        Spacer(Modifier.height(20.dp))
        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Foto Tomada",
                Modifier.size(300.dp)
            )
        }
    }

}
